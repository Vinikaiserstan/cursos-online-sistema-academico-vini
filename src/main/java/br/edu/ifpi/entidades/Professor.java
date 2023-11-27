package br.edu.ifpi.entidades;

import br.edu.ifpi.dao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Professor extends Usuario {

    public Professor(String nome, String id, String email, String tipo) {
        super(nome, id, email, tipo);
    }

    public void associarDisciplinaNoBancoDeDados(String disciplina) {
        String sql = "INSERT INTO disciplinas_professor (id_professor, disciplina) VALUES (?, ?)";
        try (Connection connection = Conexao.getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, this.getId());
            statement.setString(2, disciplina);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void darNotaNoBancoDeDados(Aluno aluno, String disciplina, int nota) {
        String sql = "INSERT INTO notas (id_aluno, disciplina, nota) VALUES (?, ?, ?)";
        try (Connection connection = Conexao.getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, aluno.getId());
            statement.setString(2, disciplina);
            statement.setInt(3, nota);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}
