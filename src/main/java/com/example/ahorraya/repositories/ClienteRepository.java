package com.example.ahorraya.repositories;

import com.example.ahorraya.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    Optional<ClienteEntity> findByUsuario_IdUsuario(Long idUsuario);

    Optional<ClienteEntity> findByUsuario_Correo(String correo);

}
