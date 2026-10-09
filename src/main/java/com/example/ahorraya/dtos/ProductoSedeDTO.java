package com.example.ahorraya.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoSedeDTO {

    private Long idProductoSede;

    private ProductoDTO producto;

    private SedeDTO sede;

    private BigDecimal precioActual;

    private Boolean disponible;

    private LocalDate fechaActualizacion;

}
