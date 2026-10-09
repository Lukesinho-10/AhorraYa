package com.example.ahorraya.services;

import com.example.ahorraya.dtos.AuthResponseDTO;
import com.example.ahorraya.dtos.LoginDTO;
import com.example.ahorraya.dtos.UsuarioRegistroDTO;
import com.example.ahorraya.dtos.UsuarioResponseDTO;

public interface UsuarioService {

    AuthResponseDTO registrar(UsuarioRegistroDTO dto);

    AuthResponseDTO login(LoginDTO dto);

    UsuarioResponseDTO buscarPorId(Long idUsuario);

}
