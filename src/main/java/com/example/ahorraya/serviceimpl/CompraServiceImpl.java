package com.example.ahorraya.serviceimpl;

import com.example.ahorraya.dtos.CompraDTO;
import com.example.ahorraya.dtos.CompraDetalleDTO;
import com.example.ahorraya.dtos.CompraDetalleRegistroDTO;
import com.example.ahorraya.dtos.CompraRegistroDTO;
import com.example.ahorraya.entities.*;
import com.example.ahorraya.exceptions.RecursoNoEncontradoException;
import com.example.ahorraya.repositories.*;
import com.example.ahorraya.services.CompraService;
import com.example.ahorraya.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CompraServiceImpl implements CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private CompraDetalleRepository compraDetalleRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoSedeRepository productoSedeRepository;

    @Autowired
    private ProductoService productoService;

    @Override
    public List<CompraDTO> listarPorCliente(Long idCliente) {
        return compraRepository.findByCliente_IdCliente(idCliente).stream()
                .map(this::convertirADTO).collect(Collectors.toList());
    }

    @Override
    public CompraDTO buscarPorId(Long idCompra) {
        return convertirADTO(compraRepository.findById(idCompra)
                .orElseThrow(() -> new RecursoNoEncontradoException("Compra no encontrada: " + idCompra)));
    }

    @Override
    public CompraDTO registrar(Long idCliente, CompraRegistroDTO dto) {
        ClienteEntity cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado: " + idCliente));
        SedeEntity sede = sedeRepository.findById(dto.getIdSede())
                .orElseThrow(() -> new RecursoNoEncontradoException("Sede no encontrada: " + dto.getIdSede()));

        CompraEntity compra = new CompraEntity();
        compra.setCliente(cliente);
        compra.setSede(sede);
        compra.setFecha(LocalDateTime.now());
        compra = compraRepository.save(compra);

        for (CompraDetalleRegistroDTO detalleDTO : dto.getDetalles()) {
            ProductoEntity producto = productoRepository.findById(detalleDTO.getIdProducto())
                    .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado: " + detalleDTO.getIdProducto()));

            ProductoSedeEntity productoSede = productoSedeRepository.findByProducto_IdProducto(detalleDTO.getIdProducto()).stream()
                    .filter(ps -> ps.getSede().getIdSede().equals(dto.getIdSede()))
                    .findFirst()
                    .orElseThrow(() -> new RecursoNoEncontradoException(
                            "El producto " + detalleDTO.getIdProducto() + " no está disponible en la sede " + dto.getIdSede()));

            CompraDetalleEntity detalle = new CompraDetalleEntity();
            detalle.setCompra(compra);
            detalle.setProducto(producto);
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setPrecioUnitario(productoSede.getPrecioActual()); // precio congelado al momento de la compra
            compraDetalleRepository.save(detalle);
        }

        return convertirADTO(compra);
    }

    private CompraDTO convertirADTO(CompraEntity entity) {
        CompraDTO dto = new CompraDTO();
        dto.setIdCompra(entity.getIdCompra());
        dto.setFecha(entity.getFecha());
        dto.setNombreSede(entity.getSede().getNombre());
        dto.setDetalles(compraDetalleRepository.findByCompra_IdCompra(entity.getIdCompra()).stream()
                .map(this::convertirDetalleADTO).collect(Collectors.toList()));
        return dto;
    }

    private CompraDetalleDTO convertirDetalleADTO(CompraDetalleEntity entity) {
        CompraDetalleDTO dto = new CompraDetalleDTO();
        dto.setIdDetalle(entity.getIdDetalle());
        dto.setProducto(productoService.buscarPorId(entity.getProducto().getIdProducto()));
        dto.setCantidad(entity.getCantidad());
        dto.setPrecioUnitario(entity.getPrecioUnitario());
        dto.setSubtotal(entity.getPrecioUnitario().multiply(BigDecimal.valueOf(entity.getCantidad())));
        return dto;
    }

}
