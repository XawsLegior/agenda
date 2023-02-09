package com.worrigan.agenda.contatos.funcoes;

import com.worrigan.agenda.MainController;
import com.worrigan.agenda.contatos.Contato;
import com.worrigan.agenda.contatos.ContatoApplication;
import com.worrigan.agenda.contatos.ContatoController;
import javafx.beans.Observable;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Editar{
    public static void carregar(int id, ContatoController parent, MainController main){
        Contato item = main.getTableContato().getItems().get(id);
        parent.nomeContato.setText(item.getNome());
        parent.telefoneContato.setText(item.getTelefone());
        parent.emailContato.setText(item.getEmail());
        parent.anotContato.setText(item.getObservacao());
    }

    public static void salvar(ContatoController parent, MainController main) throws IOException {
        int id = main.getTableContato().selectionModelProperty().get().getSelectedIndex();
        String nome = parent.nomeContato.getText();
        String telefone = parent.telefoneContato.getText();
        String email = parent.emailContato.getText();
        String observacao = parent.anotContato.getText();
        main.getTableContato().getItems().set(id, new Contato(nome, telefone, email, observacao));

        ObservableList<Contato> itens = main.getTableContato().getItems();
        FileWriter editado = new FileWriter("contatos/contatos.ini");
        String separador = Contato.separador;
        for(Contato item: itens){
            String dados = item.getNome() + separador + item.getTelefone() + separador + item.getEmail() + separador + item.getObservacao();
            editado.write(dados);
            editado.write("\n");
        }
        editado.close();
        ContatoApplication.close();
    }
}
