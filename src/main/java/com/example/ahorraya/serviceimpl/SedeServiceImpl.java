package com.example.ahorraya.serviceimpl;


import com.example.ahorraya.dtos.SedeDTO;
import com.example.ahorraya.entities.EmpresaEntity;
import com.example.ahorraya.entities.SedeEntity;
import com.example.ahorraya.exceptions.RecursoNoEncontradoException;
import com.example.ahorraya.repositories.EmpresaRepository;
import com.example.ahorraya.repositories.SedeRepository;
import com.example.ahorraya.services.SedeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SedeServiceImpl implements SedeService {

    @Autowired
    private SedeRepository sedeRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public List<SedeDTO> listar() {
        return sedeRepository.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    @Override
    public SedeDTO buscarPorId(Long idSede) {
        return convertirADTO(sedeRepository.findById(idSede)
                .orElseThrow(() -> new RecursoNoEncontradoException("Sede no encontrada: " + idSede)));
    }

    @Override
    public SedeDTO guardar(SedeDTO dto) {
        SedeEntity entity = new SedeEntity();
        entity.setIdSede(dto.getIdSede());
        entity.setNombre(dto.getNombre());
        entity.setDireccion(dto.getDireccion());
        entity.setDistrito(dto.getDistrito());

        EmpresaEntity empresa = empresaRepository.findById(dto.getIdEmpresa())
                .orElseThrow(() -> new RecursoNoEncontradoException("Empresa no encontrada: " + dto.getIdEmpresa()));
        entity.setEmpresa(empresa);

        return convertirADTO(sedeRepository.save(entity));
    }

    @Override
    public void eliminar(Long idSede) {
        sedeRepository.deleteById(idSede);
    }

    private SedeDTO convertirADTO(SedeEntity entity) {
        SedeDTO dto = new SedeDTO();
        dto.setIdSede(entity.getIdSede());
        dto.setNombre(entity.getNombre());
        dto.setDireccion(entity.getDireccion());
        dto.setDistrito(entity.getDistrito());
        dto.setIdEmpresa(entity.getEmpresa().getIdEmpresa());
        dto.setNombreEmpresa(entity.getEmpresa().getNombre());
        return dto;
    }

}
