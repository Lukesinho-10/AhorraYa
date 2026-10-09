package com.example.ahorraya.repositories;

import com.example.ahorraya.entities.SedeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SedeRepository extends JpaRepository<SedeEntity, Long> {
}
