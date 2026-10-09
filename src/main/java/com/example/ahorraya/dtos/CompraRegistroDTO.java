package com.example.ahorraya.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraRegistroDTO {

    private Long idSede;

    private List<CompraDetalleRegistroDTO> detalles;

}
