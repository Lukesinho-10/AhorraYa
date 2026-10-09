package com.example.ahorraya.serviceimpl;

import com.example.ahorraya.dtos.ProductoDTO;
import com.example.ahorraya.entities.CategoriaProductoEntity;
import com.example.ahorraya.entities.MarcaEntity;
import com.example.ahorraya.entities.ProductoEntity;
import com.example.ahorraya.entities.UnidadMedidaEnum;
import com.example.ahorraya.exceptions.RecursoNoEncontradoException;
import com.example.ahorraya.repositories.CategoriaProductoRepository;
import com.example.ahorraya.repositories.MarcaRepository;
import com.example.ahorraya.repositories.ProductoRepository;
import com.example.ahorraya.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaProductoRepository categoriaProductoRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    public List<ProductoDTO> listar() {
        return productoRepository.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    @Override
    public ProductoDTO buscarPorId(Long idProducto) {
        return convertirADTO(productoRepository.findById(idProducto)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado: " + idProducto)));
    }

    @Override
    public List<ProductoDTO> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(this::convertirADTO).collect(Collectors.toList());
    }

    @Override
    public ProductoDTO guardar(ProductoDTO dto) {
        ProductoEntity entity = new ProductoEntity();
        entity.setIdProducto(dto.getIdProducto());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        if (dto.getUnidadMedida() != null) {
            entity.setUnidadMedida(UnidadMedidaEnum.valueOf(dto.getUnidadMedida()));
        }

        CategoriaProductoEntity categoria = categoriaProductoRepository.findById(dto.getIdCategoria())
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoría no encontrada: " + dto.getIdCategoria()));
        entity.setCategoria(categoria);

        if (dto.getIdMarca() != null) {
            MarcaEntity marca = marcaRepository.findById(dto.getIdMarca())
                    .orElseThrow(() -> new RecursoNoEncontradoException("Marca no encontrada: " + dto.getIdMarca()));
            entity.setMarca(marca);
        }

        return convertirADTO(productoRepository.save(entity));
    }

    @Override
    public void eliminar(Long idProducto) {
        productoRepository.deleteById(idProducto);
    }

    private ProductoDTO convertirADTO(ProductoEntity entity) {
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProducto(entity.getIdProducto());
        dto.setNombre(entity.getNombre());
        dto.setDescripcion(entity.getDescripcion());
        dto.setUnidadMedida(entity.getUnidadMedida() != null ? entity.getUnidadMedida().name() : null);
        dto.setIdCategoria(entity.getCategoria().getIdCategoria());
        dto.setNombreCategoria(entity.getCategoria().getNombre());
        if (entity.getMarca() != null) {
            dto.setIdMarca(entity.getMarca().getIdMarca());
            dto.setNombreMarca(entity.getMarca().getNombre());
        }
        return dto;
    }

}
