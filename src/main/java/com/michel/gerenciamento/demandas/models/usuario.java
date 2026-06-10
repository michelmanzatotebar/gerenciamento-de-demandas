package com.michel.gerenciamento.demandas.models;

public class usuario {

    // Atributos
    private String nome;
    private String funcao;
    private String status;

    // Construtor
    public usuario(String nome, String funcao, String status) {
        this.nome = nome;
        this.funcao = funcao;
        this.status = status;
    }


    public String getNome() {
        return nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public String getStatus() {
        return status;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
