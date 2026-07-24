package model;

public class Voluntario {
    private int id;
    private String nome;
    private String telefone;

    public Voluntario(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void exibirDetalhesVoluntario() {
        System.out.println("Nome: " + nome);
        System.out.println("Telefone: " + telefone);
    }
}