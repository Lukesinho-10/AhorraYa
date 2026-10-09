package com.example.ahorraya.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResenaDTO {

    private Long idResena;

    private Integer valor;

    private String comentario;

    private LocalDateTime fecha;

    private String nombreProducto;

}
