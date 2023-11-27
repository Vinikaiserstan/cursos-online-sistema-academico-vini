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

        Aluno aluno1 = new Aluno("Vinicius", "aluno1", "vinicius@email.com", "aluno");
        aluno1.setSenha("senhaAluno1");

        Professor professor1 = new Professor("ProfSilva", "professor1", "silva@email.com", "professor");
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
                    return new Usuario(resultSet.getString("nome"), resultSet.getString("id"),
                            resultSet.getString("email"), resultSet.getString("tipo"));
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
                    aluno.verDisciplinasMatriculadasNoBancoDeDados();
                    break;
                case 2:
                    aluno.verNotasNoBancoDeDados();
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
                    String disciplina = scanner.nextLine();
                    professor.associarDisciplinaNoBancoDeDados(disciplina);
                    System.out.println("Disciplina associada com sucesso!");
                    break;
                case 2:
                    System.out.print("Digite o ID do aluno: ");
                    String idAluno = scanner.nextLine();
                    System.out.print("Digite a disciplina: ");
                    disciplina = scanner.nextLine();
                    System.out.print("Digite a nota: ");
                    int nota = scanner.nextInt();
                    scanner.nextLine();
                    Aluno aluno = procurarAluno(curso, idAluno);
                    if (aluno != null) {
                        professor.darNotaNoBancoDeDados(aluno, disciplina, nota);
                    } else {
                        System.out.println("Aluno não encontrado.");
                    }
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

    private static Aluno procurarAluno(Curso curso, String idAluno) {
        String sql = "SELECT * FROM alunos WHERE id = ?";
        try (Connection connection = Conexao.getConexao();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, idAluno);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Aluno(
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
