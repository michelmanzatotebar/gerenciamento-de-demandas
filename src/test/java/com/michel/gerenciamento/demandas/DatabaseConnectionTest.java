package com.michel.gerenciamento.demandas;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DatabaseConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Test
    void shouldConnectToConfiguredDatabaseAndSaveTestTable() throws SQLException {
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("SELECT 1");
                ResultSet resultSet = statement.executeQuery()) {

            DatabaseMetaData metadata = connection.getMetaData();

            assertThat(connection.isValid(2)).isTrue();
            assertThat(metadata.getDatabaseProductName()).containsIgnoringCase("PostgreSQL");
            assertThat(metadata.getURL()).startsWith("jdbc:postgresql:");
            assertThat(resultSet.next()).isTrue();
            assertThat(resultSet.getInt(1)).isEqualTo(1);

            UUID id = UUID.randomUUID();

            try (PreparedStatement createTable = connection.prepareStatement("""
                    CREATE TABLE IF NOT EXISTS teste_conexao_banco (
                        id UUID PRIMARY KEY,
                        mensagem VARCHAR(255) NOT NULL,
                        criado_em TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
                    )
                    """)) {
                createTable.executeUpdate();
            }

            try (PreparedStatement insert = connection.prepareStatement("""
                    INSERT INTO teste_conexao_banco (id, mensagem)
                    VALUES (?, ?)
                    """)) {
                insert.setObject(1, id);
                insert.setString(2, "Teste de conexao com o banco");

                assertThat(insert.executeUpdate()).isEqualTo(1);
            }

            try (PreparedStatement find = connection.prepareStatement("""
                    SELECT mensagem
                    FROM teste_conexao_banco
                    WHERE id = ?
                    """)) {
                find.setObject(1, id);

                try (ResultSet savedRecord = find.executeQuery()) {
                    assertThat(savedRecord.next()).isTrue();
                    assertThat(savedRecord.getString("mensagem")).isEqualTo("Teste de conexao com o banco");
                }
            }
        }
    }
}
