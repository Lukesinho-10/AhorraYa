package com.example.ahorraya.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "resenas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResenaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResena;

    @OneToOne
    @JoinColumn(name = "id_detalle", nullable = false, unique = true)
    private CompraDetalleEntity detalle;

    @ManyToOne
    @JoinColumn(name = "id_calificacion", nullable = false)
    private CalificacionEntity calificacion;

    private String comentario;

    private LocalDateTime fecha;

}
