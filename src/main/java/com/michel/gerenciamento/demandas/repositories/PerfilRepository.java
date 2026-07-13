package com.michel.gerenciamento.demandas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.michel.gerenciamento.demandas.entity.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}
