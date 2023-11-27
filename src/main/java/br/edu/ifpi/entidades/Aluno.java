package br.edu.ifpi.entidades;

import br.edu.ifpi.dao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Aluno extends Usuario {

    public Aluno(String nome, String id, String email, String tipo) {
        super(nome, id, email, tipo);
    }

    public void verDisciplinasMatriculadasNoBancoDeDados() {
        String sql = "SELECT disciplina FROM matriculas WHERE id_aluno = ?";
        try (Connection connection = Conexao.getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, this.getId());

            try (ResultSet resultSet = statement.executeQuery()) {
                System.out.println("Disciplinas Matriculadas:");
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("disciplina"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void verNotasNoBancoDeDados() {
        String sql = "SELECT disciplina, nota FROM notas WHERE id_aluno = ?";
        try (Connection connection = Conexao.getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, this.getId());

            try (ResultSet resultSet = statement.executeQuery()) {
                System.out.println("Notas:");
                while (resultSet.next()) {
                    System.out.println("Disciplina: " + resultSet.getString("disciplina") +
                            ", Nota: " + resultSet.getInt("nota"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

}
