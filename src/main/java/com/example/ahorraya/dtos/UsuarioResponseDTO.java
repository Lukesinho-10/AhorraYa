package com.example.ahorraya.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDTO {

    private Long idUsuario;

    private String correo;

    private LocalDate fechaCreacion;

    private String estado;

    private List<String> roles;

}
