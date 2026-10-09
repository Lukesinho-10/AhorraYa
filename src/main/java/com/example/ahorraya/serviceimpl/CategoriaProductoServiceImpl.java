package com.example.ahorraya.serviceimpl;


import com.example.ahorraya.dtos.CategoriaProductoDTO;
import com.example.ahorraya.entities.CategoriaProductoEntity;
import com.example.ahorraya.exceptions.RecursoNoEncontradoException;
import com.example.ahorraya.repositories.CategoriaProductoRepository;
import com.example.ahorraya.services.CategoriaProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaProductoServiceImpl implements CategoriaProductoService {

    @Autowired
    private CategoriaProductoRepository categoriaProductoRepository;

    @Override
    public List<CategoriaProductoDTO> listar() {
        return categoriaProductoRepository.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    @Override
    public CategoriaProductoDTO buscarPorId(Long idCategoria) {
        return convertirADTO(categoriaProductoRepository.findById(idCategoria)
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoría no encontrada: " + idCategoria)));
    }

    @Override
    public CategoriaProductoDTO guardar(CategoriaProductoDTO dto) {
        CategoriaProductoEntity entity = new CategoriaProductoEntity();
        entity.setIdCategoria(dto.getIdCategoria());
        entity.setNombre(dto.getNombre());
        return convertirADTO(categoriaProductoRepository.save(entity));
    }

    @Override
    public void eliminar(Long idCategoria) {
        categoriaProductoRepository.deleteById(idCategoria);
    }

    private CategoriaProductoDTO convertirADTO(CategoriaProductoEntity entity) {
        return new CategoriaProductoDTO(entity.getIdCategoria(), entity.getNombre());
    }

}
