package com.example.ahorraya.serviceimpl;


import com.example.ahorraya.dtos.ClienteDTO;
import com.example.ahorraya.entities.ClienteEntity;
import com.example.ahorraya.exceptions.RecursoNoEncontradoException;
import com.example.ahorraya.repositories.ClienteRepository;
import com.example.ahorraya.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public ClienteDTO buscarPorId(Long idCliente) {
        return convertirADTO(clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado: " + idCliente)));
    }

    @Override
    public ClienteDTO buscarPorIdUsuario(Long idUsuario) {
        return convertirADTO(clienteRepository.findByUsuario_IdUsuario(idUsuario)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado para el usuario: " + idUsuario)));
    }

    @Override
    public ClienteDTO actualizar(Long idCliente, ClienteDTO dto) {
        ClienteEntity entity = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado: " + idCliente));
        entity.setNombres(dto.getNombres());
        entity.setApellidos(dto.getApellidos());
        entity.setTelefono(dto.getTelefono());
        return convertirADTO(clienteRepository.save(entity));
    }

    private ClienteDTO convertirADTO(ClienteEntity entity) {
        ClienteDTO dto = new ClienteDTO();
        dto.setIdCliente(entity.getIdCliente());
        dto.setNombres(entity.getNombres());
        dto.setApellidos(entity.getApellidos());
        dto.setTelefono(entity.getTelefono());
        dto.setCorreo(entity.getUsuario().getCorreo());
        return dto;
    }

    @Override
    public ClienteDTO buscarPorCorreo(String correo) {
        ClienteEntity cliente = clienteRepository.findByUsuario_Correo(correo)
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado"));
        return convertirADTO(cliente);
    }

}
