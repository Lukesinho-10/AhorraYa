package com.example.ahorraya.serviceimpl;

import com.example.ahorraya.dtos.ResenaDTO;
import com.example.ahorraya.dtos.ResenaRegistroDTO;
import com.example.ahorraya.entities.CalificacionEntity;
import com.example.ahorraya.entities.CompraDetalleEntity;
import com.example.ahorraya.entities.ResenaEntity;
import com.example.ahorraya.exceptions.RecursoNoEncontradoException;
import com.example.ahorraya.repositories.CalificacionRepository;
import com.example.ahorraya.repositories.CompraDetalleRepository;
import com.example.ahorraya.repositories.ResenaRepository;
import com.example.ahorraya.services.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ResenaServiceImpl implements ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private CompraDetalleRepository compraDetalleRepository;

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Override
    public List<ResenaDTO> listarPorProducto(Long idProducto) {
        return resenaRepository.findByDetalle_Producto_IdProducto(idProducto).stream()
                .map(this::convertirADTO).collect(Collectors.toList());
    }

    @Override
    public ResenaDTO registrar(Long idCliente, ResenaRegistroDTO dto) {
        CompraDetalleEntity detalle = compraDetalleRepository.findById(dto.getIdDetalle())
                .orElseThrow(() -> new RecursoNoEncontradoException("Compra-detalle no encontrado: " + dto.getIdDetalle()));

        if (!detalle.getCompra().getCliente().getIdCliente().equals(idCliente)) {
            throw new RuntimeException("No puedes reseñar una compra que no es tuya");
        }
        if (detalle.getResena() != null) {
            throw new RuntimeException("Esta compra ya tiene una reseña");
        }

        CalificacionEntity calificacion = calificacionRepository.findByValor(dto.getValor())
                .orElseThrow(() -> new RecursoNoEncontradoException("Calificación inválida: " + dto.getValor()));

        ResenaEntity entity = new ResenaEntity();
        entity.setDetalle(detalle);
        entity.setCalificacion(calificacion);
        entity.setComentario(dto.getComentario());
        entity.setFecha(LocalDateTime.now());

        return convertirADTO(resenaRepository.save(entity));
    }

    private ResenaDTO convertirADTO(ResenaEntity entity) {
        ResenaDTO dto = new ResenaDTO();
        dto.setIdResena(entity.getIdResena());
        dto.setValor(entity.getCalificacion().getValor());
        dto.setComentario(entity.getComentario());
        dto.setFecha(entity.getFecha());
        dto.setNombreProducto(entity.getDetalle().getProducto().getNombre());
        return dto;
    }

}
