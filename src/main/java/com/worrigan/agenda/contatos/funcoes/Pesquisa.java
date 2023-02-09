package com.worrigan.agenda.contatos.funcoes;

import com.worrigan.agenda.MainController;
import com.worrigan.agenda.contatos.Contato;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.util.List;

public class Pesquisa {
    // AÇÃO DE MOSTRAR ITENS PESQUISADOS
    public static void pesquisa(MainController main) throws IOException {
        main.getSearch().getItems().clear();
        String pesquisa = main.getSearch().editorProperty().get().getText();
        for(String contato: new Contato().getContatos()){
            String[] dados = contato.split("/");
            if(dados[0].contains(pesquisa)){
                main.getSearch().getItems().add(dados[0]);
            }
        }
        ((ComboBox<?>) main.getSearch().lookup(".combo-box")).show();

    }

    // AÇÃO AO CLICAR EM UM ITEM NA BARRA DE PESQUISA
    public static void pesquisaSelect(String itemSelecionado, MainController main) throws IOException {
        // MOSTRAR ITENS PESQUISADOS
        if(itemSelecionado != null && !itemSelecionado.isBlank() && !itemSelecionado.isEmpty()){
            main.getTableContato().getItems().clear();
            for(String contato: new Contato().getContatos()){
                String[] contatoSplit = contato.split("/");
                if(contatoSplit[0].contains(itemSelecionado)){
                    main.getTableContato().getItems().add(new Contato(contatoSplit[0], contatoSplit[1], contatoSplit[2], contatoSplit[3]));
                }
            }
        }
        else{
            main.getTableContato().getItems().clear();
            List<String> contatos = new Contato().getContatos();
            for(String contato: contatos){
                String[] dados = contato.split(Contato.separador);
                main.getTableContato().getItems().add(new Contato(dados[0], dados[1], dados[2], dados[3]));
            }
        }
    }
}
