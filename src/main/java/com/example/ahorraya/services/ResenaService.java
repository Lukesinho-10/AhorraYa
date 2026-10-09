package com.example.ahorraya.services;

import com.example.ahorraya.dtos.ResenaDTO;
import com.example.ahorraya.dtos.ResenaRegistroDTO;

import java.util.List;

public interface ResenaService {

    List<ResenaDTO> listarPorProducto(Long idProducto);

    ResenaDTO registrar(Long idCliente, ResenaRegistroDTO resenaRegistroDTO);

}
