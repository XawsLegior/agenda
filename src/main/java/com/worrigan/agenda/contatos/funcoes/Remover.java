package com.worrigan.agenda.contatos.funcoes;

import com.worrigan.agenda.MainController;
import com.worrigan.agenda.contatos.Contato;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Remover {
    public static void remover(MainController main) throws IOException {
        int id = main.tableContato.selectionModelProperty().get().getSelectedIndex();
        List<String> contatos = Files.readAllLines(Path.of("contatos/contatos.ini"));
        FileWriter removido = new FileWriter("contatos/contatos.ini");
        String separador = Contato.separador;
        Contato itemARemover = main.tableContato.getItems().get(id);
        main.tableContato.getItems().remove(id);
        int index = 0;
        for (String contato : contatos) {
            String[] dadosSplit = contato.split(separador);
            if (dadosSplit[0].equals(itemARemover.getNome())) {
                index += 1;
                continue;
            }
            String dados = dadosSplit[0] + separador + dadosSplit[1] + separador + dadosSplit[2] + separador + dadosSplit[3];
            removido.write(dados);
            removido.write("\n");
            index += 1;
        }
        removido.close();
    }
}

