package com.example.ahorraya.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {

    private Long idProducto;

    private String nombre;

    private String descripcion;

    private String unidadMedida;

    private Long idCategoria;

    private String nombreCategoria;

    private Long idMarca;

    private String nombreMarca;

}
