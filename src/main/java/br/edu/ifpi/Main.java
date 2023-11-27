package br.edu.ifpi;

import br.edu.ifpi.dao.BuscarUsuario;
import br.edu.ifpi.dao.ProcurarAluno;
import br.edu.ifpi.entidades.Aluno;
import br.edu.ifpi.entidades.Curso;
import br.edu.ifpi.entidades.Professor;
import br.edu.ifpi.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List <Usuario> usuarios = new ArrayList<>();
        Curso cursoJava = new Curso("Programação Java", "Aberto", 40);

        realizarLogin(cursoJava);
    }

    private static void realizarLogin(Curso curso) {
        System.out.print("Digite o nome de usuário: ");
        String nomeUsuario = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        // Verifica se o usuário existe e valida a senha
        Usuario usuario = BuscarUsuario.buscarUsuarioPorCredenciais(nomeUsuario, senha);
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
            realizarLogin(curso);
        }
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
                    scanner.nextLine();
                    professor.associarDisciplinaNoBancoDeDados(scanner.nextLine());
                    System.out.println("Disciplina associada com sucesso!");
                    break;
                case 2:
                    System.out.print("Digite o ID do aluno: ");
                    String idAluno = scanner.nextLine();
                    System.out.print("Digite a disciplina: ");
                    String disciplina = scanner.nextLine();
                    System.out.print("Digite a nota: ");
                    int nota = scanner.nextInt();
                    scanner.nextLine();
                    Aluno aluno = ProcurarAluno.buscarAlunoPorId(idAluno);
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
}