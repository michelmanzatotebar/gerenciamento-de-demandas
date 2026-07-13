package com.michel.gerenciamento.demandas.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michel.gerenciamento.demandas.entity.Demanda;
import com.michel.gerenciamento.demandas.repositories.DemandaRepository;

@RestController
@RequestMapping("/api/demandas")
public class DemandaController {

    private final DemandaRepository demandaRepository;

    public DemandaController(DemandaRepository demandaRepository) {
        this.demandaRepository = demandaRepository;
    }

    @GetMapping
    public List<Demanda> listar() {
        return demandaRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Demanda> criar(@RequestBody Demanda demanda) {
        Demanda salva = demandaRepository.save(demanda);
        return ResponseEntity.ok(salva);
    }
}
