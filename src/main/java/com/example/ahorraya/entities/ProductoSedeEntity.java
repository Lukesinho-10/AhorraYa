package com.example.ahorraya.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "productos_sedes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_producto", "id_sede"})
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoSedeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProductoSede;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoEntity producto;

    @ManyToOne
    @JoinColumn(name = "id_sede", nullable = false)
    private SedeEntity sede;

    private BigDecimal precioActual;

    private Boolean disponible;

    private LocalDate fechaActualizacion;

}
