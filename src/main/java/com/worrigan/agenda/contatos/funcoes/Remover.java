package com.worrigan.agenda.contatos.funcoes;

import com.worrigan.agenda.MainController;
import com.worrigan.agenda.contatos.Contato;

import java.io.FileWriter;
import java.io.IOException;

public class Remover {
    public static void remover(int id, MainController main) throws IOException {
        main.tableContato.getItems().remove(id);
        FileWriter removido = new FileWriter("contatos/contatos.ini");
        String separador = Contato.separador;
        int index = 0;
        for(Contato contato: main.tableContato.getItems()){
            if(index != id){
                String dados = contato.getNome() + separador + contato.getTelefone() + separador + contato.getEmail() + separador + contato.getObservacao();
                removido.write(dados);
                removido.write("\n");
            }
        }
        removido.close();
    }
}
