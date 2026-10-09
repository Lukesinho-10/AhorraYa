package com.example.ahorraya.services;

import com.example.ahorraya.dtos.ClienteDTO;

public interface ClienteService {

    ClienteDTO buscarPorId(Long idCliente);

    ClienteDTO buscarPorIdUsuario(Long idUsuario);

    ClienteDTO actualizar(Long idCliente, ClienteDTO clienteDTO);

    ClienteDTO buscarPorCorreo(String correo);

}
