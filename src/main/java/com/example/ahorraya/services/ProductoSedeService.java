package com.example.ahorraya.services;

import com.example.ahorraya.dtos.ProductoSedeDTO;

import java.util.List;

public interface ProductoSedeService {

    List<ProductoSedeDTO> listar();

    ProductoSedeDTO buscarPorId(Long idProductoSede);

    List<ProductoSedeDTO> buscarPorProducto(Long idProducto);

    List<ProductoSedeDTO> buscarPorDistrito(String distrito);

    ProductoSedeDTO guardar(ProductoSedeDTO productoSedeDTO);

    void eliminar(Long idProductoSede);

}
