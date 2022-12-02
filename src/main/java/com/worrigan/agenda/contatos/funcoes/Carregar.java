package com.worrigan.agenda.contatos.funcoes;

import com.worrigan.agenda.MainController;
import com.worrigan.agenda.contatos.Contato;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Carregar {
    public static void contatos(MainController main) throws IOException {
        /* CRIAR PASTA/LER CONTATOS */
        List<String> contatos = new ArrayList<>();
        File f = new File("contatos/");
        if(!f.exists()){
            f.mkdir();
        }
        else{
            try {
                contatos = Files.readAllLines(new File("contatos/contatos.ini").toPath());
            }
            catch (Exception ignored){}
        }
        /* CRIAR CELULAS */
        main.nomeContato.setCellValueFactory(new PropertyValueFactory<>("Nome"));
        main.telefoneContato.setCellValueFactory(new PropertyValueFactory<>("Telefone"));
        main.emailContato.setCellValueFactory(new PropertyValueFactory<>("Email"));
        main.anotContato.setCellValueFactory(new PropertyValueFactory<>("Observacao"));

        /* MOSTRAR CONTATO NA LISTVIEW */
        if(contatos.size() > 0){
            for (String contato: contatos){
                String[] dado = contato.split(Contato.separador);
                main.tableContato.getItems().add(new Contato(dado[0], dado[1], dado[2], dado[3]));
                main.search.getItems().add(dado[0]);
            }
        }
    }
}
