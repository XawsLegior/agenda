package com.worrigan.agenda.contatos.funcoes;

import com.worrigan.agenda.MainController;
import com.worrigan.agenda.contatos.Contato;
import com.worrigan.agenda.contatos.ContatoController;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class Salvar {
    public static void salvar(ContatoController parent, MainController main) throws IOException {
        String separador = Contato.separador;
        String nome = parent.nomeContato.getText();
        String telefone = parent.telefoneContato.getText();
        String email = parent.emailContato.getText();
        String anot = parent.anotContato.getText();

        // CHECAR VALORES VAZIOS
        if(nome.isBlank() || nome.isEmpty()){
            parent.contatoLabel.setText("Nome não pode ficar em branco!");
            parent.contatoLabel.setStyle("-fx-text-fill: yellow");
            return;
        }
        if(telefone.isEmpty()){
            telefone = " ";
        }
        if(email.isEmpty()){
            email = " ";
        }
        if(anot.isEmpty()){
            anot = " ";
        }
        String dados = nome + separador + telefone + separador + email + separador + anot;
        FileWriter f = new FileWriter("contatos/contatos.ini", true);
        f.write(dados);
        f.write("\n");
        f.close();

        main.getTableContato().getItems().add(new Contato(nome, telefone, email, anot));
        parent.contatoLabel.setText("%s foi adicionado.".formatted(nome));
    }
}
