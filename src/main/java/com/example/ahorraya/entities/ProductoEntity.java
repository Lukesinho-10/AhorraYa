package com.example.ahorraya.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    private String nombre;

    private String descripcion;

    @Enumerated(EnumType.STRING)
    private UnidadMedidaEnum unidadMedida;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private MarcaEntity marca;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private CategoriaProductoEntity categoria;

    @OneToMany(mappedBy = "producto", fetch = FetchType.EAGER)
    private List<CompraDetalleEntity> compraDetalle;

    @OneToMany(mappedBy = "producto")
    private List<ProductoSedeEntity> productoSede;

}
