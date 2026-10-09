package com.example.ahorraya.services;

import com.example.ahorraya.dtos.CategoriaProductoDTO;

import java.util.List;

public interface CategoriaProductoService {

    List<CategoriaProductoDTO> listar();

    CategoriaProductoDTO buscarPorId(Long idCategoria);

    CategoriaProductoDTO guardar(CategoriaProductoDTO categoriaProductoDTO);

    void eliminar(Long idCategoria);

}
