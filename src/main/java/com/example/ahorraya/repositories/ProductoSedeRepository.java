package com.example.ahorraya.repositories;


import com.example.ahorraya.entities.ProductoSedeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoSedeRepository extends JpaRepository<ProductoSedeEntity, Long> {

    List<ProductoSedeEntity> findByProducto_IdProducto(Long idProducto);

    List<ProductoSedeEntity> findBySede_Distrito(String distrito);

    // Ordenados por precio ascendente: los usa ProductoSedeServiceImpl (búsqueda/filtros)
    List<ProductoSedeEntity> findByProducto_IdProductoOrderByPrecioActualAsc(Long idProducto);

    List<ProductoSedeEntity> findBySede_DistritoOrderByPrecioActualAsc(String distrito);

}
