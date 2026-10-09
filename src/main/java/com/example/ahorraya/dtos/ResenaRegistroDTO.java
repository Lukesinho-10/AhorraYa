package com.example.ahorraya.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResenaRegistroDTO {

    private Long idDetalle;

    private Integer valor;

    private String comentario;

}
