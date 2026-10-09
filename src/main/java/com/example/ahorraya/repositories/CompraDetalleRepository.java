package com.example.ahorraya.repositories;

import com.example.ahorraya.entities.CompraDetalleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompraDetalleRepository extends JpaRepository<CompraDetalleEntity, Long> {

    List<CompraDetalleEntity> findByCompra_IdCompra(Long idCompra);

}
