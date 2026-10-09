package com.example.ahorraya.serviceimpl;

import com.example.ahorraya.dtos.ProductoSedeDTO;
import com.example.ahorraya.entities.ProductoEntity;
import com.example.ahorraya.entities.ProductoSedeEntity;
import com.example.ahorraya.entities.SedeEntity;
import com.example.ahorraya.exceptions.RecursoNoEncontradoException;
import com.example.ahorraya.repositories.ProductoRepository;
import com.example.ahorraya.repositories.ProductoSedeRepository;
import com.example.ahorraya.repositories.SedeRepository;
import com.example.ahorraya.services.ProductoSedeService;
import com.example.ahorraya.services.ProductoService;
import com.example.ahorraya.services.SedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductoSedeServiceImpl implements ProductoSedeService {

    @Autowired
    private ProductoSedeRepository productoSedeRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private SedeService sedeService;

    @Override
    public List<ProductoSedeDTO> listar() {
        return productoSedeRepository.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    @Override
    public ProductoSedeDTO buscarPorId(Long idProductoSede) {
        return convertirADTO(productoSedeRepository.findById(idProductoSede)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto-Sede no encontrado: " + idProductoSede)));
    }

    @Override
    public List<ProductoSedeDTO> buscarPorProducto(Long idProducto) {
        List<ProductoSedeEntity> lista = productoSedeRepository.findByProducto_IdProductoOrderByPrecioActualAsc(idProducto);
        return lista.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductoSedeDTO> buscarPorDistrito(String distrito) {
        List<ProductoSedeEntity> lista = productoSedeRepository.findBySede_DistritoOrderByPrecioActualAsc(distrito);
        return lista.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoSedeDTO guardar(ProductoSedeDTO dto) {
        // dto.getProducto().getIdProducto() y dto.getSede().getIdSede() son obligatorios al crear
        ProductoSedeEntity entity = new ProductoSedeEntity();
        entity.setIdProductoSede(dto.getIdProductoSede());
        entity.setPrecioActual(dto.getPrecioActual());
        entity.setDisponible(dto.getDisponible());
        entity.setFechaActualizacion(LocalDate.now());

        ProductoEntity producto = productoRepository.findById(dto.getProducto().getIdProducto())
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado"));
        entity.setProducto(producto);

        SedeEntity sede = sedeRepository.findById(dto.getSede().getIdSede())
                .orElseThrow(() -> new RecursoNoEncontradoException("Sede no encontrada"));
        entity.setSede(sede);

        return convertirADTO(productoSedeRepository.save(entity));
    }

    @Override
    public void eliminar(Long idProductoSede) {
        productoSedeRepository.deleteById(idProductoSede);
    }

    private ProductoSedeDTO convertirADTO(ProductoSedeEntity entity) {
        ProductoSedeDTO dto = new ProductoSedeDTO();
        dto.setIdProductoSede(entity.getIdProductoSede());
        dto.setProducto(productoService.buscarPorId(entity.getProducto().getIdProducto()));
        dto.setSede(sedeService.buscarPorId(entity.getSede().getIdSede()));
        dto.setPrecioActual(entity.getPrecioActual());
        dto.setDisponible(entity.getDisponible());
        dto.setFechaActualizacion(entity.getFechaActualizacion());
        return dto;
    }

}
