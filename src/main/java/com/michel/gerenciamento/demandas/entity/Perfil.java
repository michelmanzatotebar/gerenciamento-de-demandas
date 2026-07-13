package com.michel.gerenciamento.demandas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "perfis")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(length = 120)
    private String local;

    @Column(length = 80)
    private String status;

    public Perfil() {
    }

    public Perfil(String nome, String local, String status) {
        this.nome = nome;
        this.local = local;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getLocal() { return local; }
    public String getStatus() { return status; }
    public void setNome(String nome) { this.nome = nome; }
    public void setLocal(String local) { this.local = local; }
    public void setStatus(String status) { this.status = status; }
}
