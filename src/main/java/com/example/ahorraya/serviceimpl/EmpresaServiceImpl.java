package com.example.ahorraya.serviceimpl;

import com.example.ahorraya.dtos.EmpresaDTO;
import com.example.ahorraya.entities.EmpresaEntity;
import com.example.ahorraya.exceptions.RecursoNoEncontradoException;
import com.example.ahorraya.repositories.EmpresaRepository;
import com.example.ahorraya.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public List<EmpresaDTO> listar() {
        return empresaRepository.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    @Override
    public EmpresaDTO buscarPorId(Long idEmpresa) {
        return convertirADTO(empresaRepository.findById(idEmpresa)
                .orElseThrow(() -> new RecursoNoEncontradoException("Empresa no encontrada: " + idEmpresa)));
    }

    @Override
    public EmpresaDTO guardar(EmpresaDTO dto) {
        EmpresaEntity entity = new EmpresaEntity();
        entity.setIdEmpresa(dto.getIdEmpresa());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());
        return convertirADTO(empresaRepository.save(entity));
    }

    @Override
    public void eliminar(Long idEmpresa) {
        empresaRepository.deleteById(idEmpresa);
    }

    private EmpresaDTO convertirADTO(EmpresaEntity entity) {
        return new EmpresaDTO(entity.getIdEmpresa(), entity.getNombre(), entity.getDescripcion());
    }

}
