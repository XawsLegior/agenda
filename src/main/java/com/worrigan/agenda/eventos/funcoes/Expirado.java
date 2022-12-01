package com.worrigan.agenda.eventos.funcoes;

import com.worrigan.agenda.MainController;
import com.worrigan.agenda.eventos.Evento;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileWriter;
import java.io.IOException;

public class Expirado {
    /* EVENTO JÁ INICIADO */
    /* MOVER PARA EXPIRADO & DELETAR DOS EVENTOS ATIVOS */
    public static void expirado(String id, MainController parent) throws IOException {
        String proximoId = String.valueOf(parent.tableEvento2.getItems().size());

        // SALVAR EXPIRADOS & REMOVER
        FileWriter expiradosFile = new FileWriter("eventos/expirados.ini", true);
        String dadosEvento = Evento.getEventoWithId(Integer.parseInt(id));
        String[] dados = dadosEvento.split(Evento.getSeparador());
        String separador = Evento.getSeparador();
        dadosEvento = proximoId + separador + dados[1] + separador + dados[2] + separador + dados[3];
        expiradosFile.write(dadosEvento);
        expiradosFile.write("\n");
        expiradosFile.close();
        Remover.remover(Integer.parseInt(id), parent);

        // MOSTRAR EXPIRADOS NA LIST
        String[] dadosEventoSplit = dadosEvento.split(Evento.getSeparador());
        parent.id2.setCellValueFactory(new PropertyValueFactory<>("id"));
        parent.data2.setCellValueFactory(new PropertyValueFactory<>("data"));
        parent.hora2.setCellValueFactory(new PropertyValueFactory<>("hora"));
        parent.evento2.setCellValueFactory(new PropertyValueFactory<>("evento"));
        parent.tableEvento2.getItems().add(new Evento(proximoId, dadosEventoSplit[1], dadosEventoSplit[2], dadosEventoSplit[3]));
        parent.btnZerar.setDisable(false);
    }
}
