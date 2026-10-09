package com.example.ahorraya.repositories;

import com.example.ahorraya.entities.CalificacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CalificacionRepository extends JpaRepository<CalificacionEntity, Long> {

    Optional<CalificacionEntity> findByValor(Integer valor);

}
