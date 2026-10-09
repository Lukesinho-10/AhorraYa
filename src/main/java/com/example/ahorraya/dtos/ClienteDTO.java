package com.example.ahorraya.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Long idCliente;

    private String nombres;

    private String apellidos;

    private String telefono;

    private String correo;

}
