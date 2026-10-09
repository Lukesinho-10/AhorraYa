package com.example.ahorraya.services;

import com.example.ahorraya.dtos.MarcaDTO;

import java.util.List;

public interface MarcaService {

    List<MarcaDTO> listar();

    MarcaDTO buscarPorId(Long idMarca);

    MarcaDTO guardar(MarcaDTO marcaDTO);

    void eliminar(Long idMarca);

}
