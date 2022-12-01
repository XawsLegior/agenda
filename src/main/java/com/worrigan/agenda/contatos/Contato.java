package com.worrigan.agenda.contatos;

public class Contato {
    public static String separador = "/";
    String nome;
    String telefone;
    String email;
    String observacao;

    public Contato(String nome, String telefone, String email, String anot) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.observacao = anot;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
