package br.edu.ifpi.entidades;

public class Usuario {
    private String nome;
    private String senha;
    private String tipo;  // Aluno, Professor, etc.
    private String email;

    public Usuario(String nome, String senha, String tipo, String email) {
        this.nome = nome;
        this.senha = senha;
        this.tipo = tipo;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean validarSenha(String senha) {
        return this.senha.equals(senha);
    }
}
