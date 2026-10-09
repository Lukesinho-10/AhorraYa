package com.example.ahorraya.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "categorias_productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategoria;

    private String nombre;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER)
    private List<ProductoEntity> productos;

}
