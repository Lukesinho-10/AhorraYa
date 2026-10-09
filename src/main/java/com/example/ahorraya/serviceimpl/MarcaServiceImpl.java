package com.example.ahorraya.serviceimpl;

import com.example.ahorraya.dtos.MarcaDTO;
import com.example.ahorraya.entities.MarcaEntity;
import com.example.ahorraya.exceptions.RecursoNoEncontradoException;
import com.example.ahorraya.repositories.MarcaRepository;
import com.example.ahorraya.services.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MarcaServiceImpl implements MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    public List<MarcaDTO> listar() {
        return marcaRepository.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    @Override
    public MarcaDTO buscarPorId(Long idMarca) {
        return convertirADTO(marcaRepository.findById(idMarca)
                .orElseThrow(() -> new RecursoNoEncontradoException("Marca no encontrada: " + idMarca)));
    }

    @Override
    public MarcaDTO guardar(MarcaDTO dto) {
        MarcaEntity entity = new MarcaEntity();
        entity.setIdMarca(dto.getIdMarca());
        entity.setNombre(dto.getNombre());
        return convertirADTO(marcaRepository.save(entity));
    }

    @Override
    public void eliminar(Long idMarca) {
        marcaRepository.deleteById(idMarca);
    }

    private MarcaDTO convertirADTO(MarcaEntity entity) {
        return new MarcaDTO(entity.getIdMarca(), entity.getNombre());
    }


}
