package com.example.ahorraya.services;

import com.example.ahorraya.dtos.EmpresaDTO;

import java.util.List;

public interface EmpresaService {

    List<EmpresaDTO> listar();

    EmpresaDTO buscarPorId(Long idEmpresa);

    EmpresaDTO guardar(EmpresaDTO empresaDTO);

    void eliminar(Long idEmpresa);

}
