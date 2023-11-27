package br.edu.ifpi;

import br.edu.ifpi.dao.Conexao;
import br.edu.ifpi.entidades.Aluno;
import br.edu.ifpi.entidades.Curso;
import br.edu.ifpi.entidades.Professor;
import br.edu.ifpi.entidades.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<Usuario> usuarios = new ArrayList<>();
        Curso cursoJava = new Curso("Programação Java", "Aberto", 40);

        // Exemplo de usuários
        Aluno aluno1 = new Aluno("vinicius", "aluno1", "vinicius@email.com", null);
        aluno1.setSenha("senhaAluno1");

        Professor professor1 = new Professor("ProfSilva", "professor1", "silva@email.com", null);
        professor1.setSenha("senhaProfessor1");

        usuarios.add(aluno1);
        usuarios.add(professor1);

        realizarLogin(cursoJava, usuarios);
    }

    private static void realizarLogin(Curso curso, List<Usuario> usuarios) {
        System.out.print("Digite o nome de usuário: ");
        String nomeUsuario = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        Usuario usuario = buscarUsuario(nomeUsuario, senha);
        if (usuario != null) {
            if (usuario instanceof Aluno) {
                menuAluno((Aluno) usuario, curso);
            } else if (usuario instanceof Professor) {
                menuProfessor((Professor) usuario, curso);
            } else {
                System.out.println("Tipo de usuário desconhecido.");
            }
        } else {
            System.out.println("Credenciais inválidas. Tente novamente.");
            realizarLogin(curso, usuarios);
        }
    }

    private static Usuario buscarUsuario(String nomeUsuario, String senha) {
        String sql = "SELECT * FROM usuarios WHERE nome = ? AND senha = ?";
        try (Connection connection = Conexao.getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nomeUsuario);
            statement.setString(2, senha);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    String email = resultSet.getString("email");
                    
                    
                    return new Usuario(nome, email, sql, sql);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void menuAluno(Aluno aluno, Curso curso) {
        int opcao;

        do {
            System.out.println("===== Menu Aluno =====");
            System.out.println("1. Ver Disciplinas Matriculadas");
            System.out.println("2. Ver Notas");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Implemente a lógica para exibir as disciplinas matriculadas do banco de dados");
                    break;
                case 2:
                    System.out.println("Implemente a lógica para exibir as notas do banco de dados");
                    break;
                case 3:
                    System.out.println("Saindo do sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

            System.out.println();

        } while (opcao != 3);
    }

    private static void menuProfessor(Professor professor, Curso curso) {
        int opcao;

        do {
            System.out.println("===== Menu Professor =====");
            System.out.println("1. Associar Disciplina");
            System.out.println("2. Dar Nota");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome da disciplina: ");
                    scanner.nextLine();
                    System.out.println("Implemente a lógica para associar disciplina no banco de dados");
                    break;
                case 2:
                    System.out.print("Digite o ID do aluno: ");
                    System.out.print("Digite a disciplina: ");
                    scanner.nextLine();
                    System.out.print("Digite a nota: ");
                    scanner.nextLine();
                    System.out.println("Implemente a lógica para dar nota no banco de dados");
                    break;
                case 3:
                    System.out.println("Saindo do sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

            System.out.println();

        } while (opcao != 3);
    }
}
