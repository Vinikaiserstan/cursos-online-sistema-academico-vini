package br.edu.ifpi.dao;

import br.edu.ifpi.entidades.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuscarUsuario {

    public static Usuario buscarUsuarioPorCredenciais(String nomeUsuario, String senha) {
        String sql = "SELECT * FROM usuarios WHERE nome = ? AND senha = ?";
        try (Connection connection = Conexao.getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nomeUsuario);
            statement.setString(2, senha);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Usuario(
                            resultSet.getString("nome"),
                            resultSet.getString("id"),
                            resultSet.getString("email"),
                            resultSet.getString("tipo")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
