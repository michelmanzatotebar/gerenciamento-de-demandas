package com.michel.gerenciamento.demandas.models;

public class perfil {

    // Atributos
    private String nome;
    private String local;
    private String status;

    // Construtor
    public perfil(String nome, String local, String status) {
        this.nome = nome;
        this.local = local;
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public String getLocal() {
        return local;
    }

    public String getStatus() {
        return status;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
