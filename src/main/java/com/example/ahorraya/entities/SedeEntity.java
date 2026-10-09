package com.example.ahorraya.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "sedes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SedeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSede;

    private String nombre;

    private String direccion;

    private String distrito;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private EmpresaEntity empresa;

    @OneToMany(mappedBy = "sede", fetch = FetchType.EAGER)
    private List<CompraEntity> compras;

    @OneToMany(mappedBy = "sede")
    private List<ProductoSedeEntity> productoSede;

}
