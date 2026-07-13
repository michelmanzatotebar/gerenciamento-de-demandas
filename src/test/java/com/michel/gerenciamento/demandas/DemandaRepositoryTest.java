package com.michel.gerenciamento.demandas;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.michel.gerenciamento.demandas.entity.Demanda;
import com.michel.gerenciamento.demandas.repositories.DemandaRepository;

@DataJpaTest
class DemandaRepositoryTest {

    @Autowired
    private DemandaRepository demandaRepository;

    @Test
    void shouldPersistAndLoadDemanda() {
        Demanda demanda = new Demanda();
        demanda.setNome("Nova demanda");
        demanda.setDescricao("Descrição de teste");
        demanda.setStatus("Em andamento");
        demanda.setPrioridade("Alta");
        demanda.setUsuarioResponsavel("Michel");

        Demanda saved = demandaRepository.save(demanda);

        assertThat(saved.getId()).isNotNull();
        assertThat(demandaRepository.findById(saved.getId())).isPresent();
    }
}
