package com.example.ahorraya.services;

import com.example.ahorraya.dtos.ProductoDTO;

import java.util.List;

public interface ProductoService {

    List<ProductoDTO> listar();

    ProductoDTO buscarPorId(Long idProducto);

    List<ProductoDTO> buscarPorNombre(String nombre);

    ProductoDTO guardar(ProductoDTO productoDTO);

    void eliminar(Long idProducto);

}
