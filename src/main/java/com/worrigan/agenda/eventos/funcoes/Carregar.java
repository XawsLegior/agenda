package com.worrigan.agenda.eventos.funcoes;

import com.worrigan.agenda.MainController;
import com.worrigan.agenda.eventos.Evento;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Carregar {
    public static void carregar(MainController parent) throws IOException {
        parent.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        parent.data.setCellValueFactory(new PropertyValueFactory<>("data"));
        parent.hora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        parent.evento.setCellValueFactory(new PropertyValueFactory<>("evento"));

        if (!new File("eventos/").exists()) {
            new File("eventos/").mkdir();
        } else {
            File f = new File("eventos/eventos.ini");
            if (f.exists()) {
                List<String> dados = Files.readAllLines(f.toPath());
                for (String dado : dados) {
                    dado = dado.replace("\\n", "\n");
                    String[] dadoSplit = dado.split(Evento.getSeparador());
                    String evento = new Evento(dadoSplit[0], dadoSplit[1], dadoSplit[2], dadoSplit[3]).getEvento();
                    parent.tableEvento.getItems().add(new Evento(dadoSplit[0], dadoSplit[1], dadoSplit[2], evento));
                }
            }
        }
    }

    public static void expirados(MainController parent) throws IOException {
        parent.id2.setCellValueFactory(new PropertyValueFactory<>("id"));
        parent.data2.setCellValueFactory(new PropertyValueFactory<>("data"));
        parent.hora2.setCellValueFactory(new PropertyValueFactory<>("hora"));
        parent.evento2.setCellValueFactory(new PropertyValueFactory<>("evento"));

        File expirados = new File("eventos/expirados.ini");
        if (expirados.exists()) {
            List<String> dados = Files.readAllLines(expirados.toPath());
            if (dados.size() > 0) {
                for (String dado : dados) {
                    String[] conteudo = dado.split(Evento.getSeparador());
                    String evento = new Evento(conteudo[0], conteudo[1], conteudo[2], conteudo[3]).getEvento();
                    parent.tableEvento2.getItems().add(new Evento(conteudo[0], conteudo[1], conteudo[2], evento));
                }
                parent.btnZerar.setDisable(false);
            }
        }
    }
}
