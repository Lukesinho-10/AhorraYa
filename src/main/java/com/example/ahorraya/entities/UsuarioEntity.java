package com.example.ahorraya.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String correo;

    private String password;

    private LocalDate fechaCreacion;

    @Enumerated(EnumType.STRING)
    private EstadoUsuarioEnum estado;

    @ToString.Exclude
    @JsonIgnore
    @OneToOne(mappedBy = "usuario", fetch = FetchType.EAGER)
    private ClienteEntity cliente;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
    private List<RolEntity> roles;


}
