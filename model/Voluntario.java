package model;

public class Voluntario {
    private String nome;
    private int telefone;

    public Voluntario(String nome, int telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public void exibirDetalhesVoluntario() {
        System.out.println("Nome: " + nome);
        System.out.println("Telefone: " + telefone);
    }
}