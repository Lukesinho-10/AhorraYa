package com.example.ahorraya.repositories;

import com.example.ahorraya.entities.ResenaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ResenaRepository extends JpaRepository<ResenaEntity, Long> {

    List<ResenaEntity> findByDetalle_Producto_IdProducto(Long idProducto);

}
