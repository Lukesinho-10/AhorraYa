package com.example.ahorraya.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraDTO {

    private Long idCompra;

    private LocalDateTime fecha;

    private String nombreSede;

    private List<CompraDetalleDTO> detalles;

}
