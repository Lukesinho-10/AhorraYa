package com.example.ahorraya.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "compras_detalles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraDetalleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalle;

    @ManyToOne
    @JoinColumn(name = "id_compra", nullable = false)
    private CompraEntity compra;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoEntity producto;

    private Integer cantidad;

    private BigDecimal precioUnitario;

    @OneToOne(mappedBy = "detalle")
    private ResenaEntity resena;

}
