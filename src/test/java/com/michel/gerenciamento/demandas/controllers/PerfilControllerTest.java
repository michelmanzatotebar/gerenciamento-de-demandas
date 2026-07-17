package com.michel.gerenciamento.demandas.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.test.web.servlet.MockMvc;

import com.michel.gerenciamento.demandas.entity.Perfil;
import com.michel.gerenciamento.demandas.repositories.PerfilRepository;

@WebMvcTest(PerfilController.class)
class PerfilControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PerfilRepository perfilRepository;

    @Test
    void listarQuandoNaoHaPerfisRetornaMensagem() throws Exception {
        when(perfilRepository.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/perfis"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Nenhum perfil encontrado, crie um para começar")));
    }

    @Test
    void listarQuandoNaoHaConexaoRetornaMensagemDeErro() throws Exception {
        when(perfilRepository.findAll()).thenThrow(new DataAccessException("db unavailable") {
        });

        mockMvc.perform(get("/api/perfis"))
                .andExpect(status().isServiceUnavailable())
                .andExpect(content().string(containsString("sem conexao com banco")));
    }
}
