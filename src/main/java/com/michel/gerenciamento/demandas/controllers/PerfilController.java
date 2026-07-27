package com.michel.gerenciamento.demandas.controllers;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> listar() {
        try {
            List<Perfil> perfis = perfilRepository.findAll();
            if (perfis.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body("Nenhum perfil encontrado, crie um para começar");
            }
            return ResponseEntity.ok(perfis);
        } catch (DataAccessException ex) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("sem conexao com banco");
        }
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Perfil perfil) {
    try {
        Perfil salvo = perfilRepository.save(perfil);
        return ResponseEntity.ok(salvo);
    } catch (DataAccessException ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("sem conexao com banco");
    }
    }
}
