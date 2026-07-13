package com.michel.gerenciamento.demandas.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michel.gerenciamento.demandas.entity.Perfil;
import com.michel.gerenciamento.demandas.repositories.PerfilRepository;

@RestController
@RequestMapping("/api/perfis")
public class PerfilController {

    private final PerfilRepository perfilRepository;

    public PerfilController(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    @GetMapping
    public List<Perfil> listar() {
        return perfilRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Perfil> criar(@RequestBody Perfil perfil) {
        Perfil salvo = perfilRepository.save(perfil);
        return ResponseEntity.ok(salvo);
    }
}
