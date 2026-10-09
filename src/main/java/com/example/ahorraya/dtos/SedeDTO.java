package com.example.ahorraya.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SedeDTO {

    private Long idSede;

    private String nombre;

    private String direccion;

    private String distrito;

    private Long idEmpresa;

    private String nombreEmpresa;

}
