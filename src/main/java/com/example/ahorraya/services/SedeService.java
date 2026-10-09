package com.example.ahorraya.services;

import com.example.ahorraya.dtos.SedeDTO;

import java.util.List;

public interface SedeService {

    List<SedeDTO> listar();

    SedeDTO buscarPorId(Long idSede);

    SedeDTO guardar(SedeDTO sedeDTO);

    void eliminar(Long idSede);

}
