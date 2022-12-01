package com.worrigan.agenda.eventos.funcoes;

import com.worrigan.agenda.MainController;
import com.worrigan.agenda.eventos.Evento;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Remover {
    public static void remover(int id, MainController parent) throws IOException {
        List<String> dados = Files.readAllLines(new File("eventos/eventos.ini").toPath());
        FileWriter newf = new FileWriter("eventos/eventos.ini");
        parent.tableEvento.getItems().clear();
        int index = 0;
        try {
            for (String dado : dados) {
                if (!dado.startsWith(String.valueOf(id))) {
                    String[] dadoSplit = dado.split(Evento.getSeparador());
                    dadoSplit[0] = index + Evento.getSeparador();
                    String separador = Evento.getSeparador();
                    String novoDado = index + separador + dadoSplit[1] + separador + dadoSplit[2] + separador + dadoSplit[3];
                    newf.write(novoDado);
                    newf.write("\n");
                    parent.tableEvento.getItems().add(new Evento(String.valueOf(index), dadoSplit[1], dadoSplit[2], dadoSplit[3]));
                    index += 1;
                }
            }
            newf.close();
        } catch (Exception ignored) {}
    }
}