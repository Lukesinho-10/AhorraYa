package com.example.ahorraya.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "clientes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    private String nombres;

    private String apellidos;

    private String telefono;

    @OneToOne
    @JoinColumn(name = "id_usuario", unique = true)
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private List<CompraEntity> compras;

}
