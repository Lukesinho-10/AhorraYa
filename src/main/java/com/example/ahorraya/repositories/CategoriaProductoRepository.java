package com.example.ahorraya.repositories;

import com.example.ahorraya.entities.CategoriaProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoriaProductoRepository extends JpaRepository<CategoriaProductoEntity, Long> {
}
