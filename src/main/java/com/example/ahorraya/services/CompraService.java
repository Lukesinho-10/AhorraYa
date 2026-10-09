package com.example.ahorraya.services;

import com.example.ahorraya.dtos.CompraDTO;
import com.example.ahorraya.dtos.CompraRegistroDTO;

import java.util.List;

public interface CompraService {

    List<CompraDTO> listarPorCliente(Long idCliente);

    CompraDTO buscarPorId(Long idCompra);

    CompraDTO registrar(Long idCliente, CompraRegistroDTO compraRegistroDTO);

}
