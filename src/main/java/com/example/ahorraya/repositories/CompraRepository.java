package com.example.ahorraya.repositories;

import com.example.ahorraya.entities.CompraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompraRepository extends JpaRepository<CompraEntity, Long> {

    List<CompraEntity> findByCliente_IdCliente(Long idCliente);

}
