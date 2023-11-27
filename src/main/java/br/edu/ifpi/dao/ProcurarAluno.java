package br.edu.ifpi.dao;

import br.edu.ifpi.entidades.Aluno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcurarAluno {

    public static Aluno buscarAlunoPorId(String idAluno) {
        String sql = "SELECT * FROM alunos WHERE id = ?";
        try (Connection connection = Conexao.getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, idAluno);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Aluno aluno = new Aluno(
                            resultSet.getString("nome"),
                            resultSet.getString("id"),
                            resultSet.getString("email"),
                            resultSet.getString("tipo")
                    );
                    aluno.setSenha(resultSet.getString("senha"));
                    return aluno;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
