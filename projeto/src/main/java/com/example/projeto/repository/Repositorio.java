package com.example.projeto.repository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.projeto.entities.Entities;

@Repository
public interface Repositorio extends JpaRepository<Entities, UUID>{

}
