package com.michel.gerenciamento.demandas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.michel.gerenciamento.demandas.entity.Demanda;

@Repository
public interface DemandaRepository extends JpaRepository<Demanda, Long> {
}
