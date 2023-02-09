package com.worrigan.agenda.eventos.funcoes;

import com.worrigan.agenda.eventos.Evento;
import com.worrigan.agenda.eventos.EventoController;

import java.io.IOException;
import java.time.LocalDate;

public class Visualizar {
    public static void visualizar(int id, EventoController parent) throws IOException {
        parent.getBtnSalvar().setVisible(false);
        parent.getDatePicker().setEditable(false);
        parent.getDatePicker().setOnMouseClicked(event -> parent.getDatePicker().hide());
        parent.getHora().setEditable(false);
        parent.getEvento().setEditable(false);
        String dados = Evento.getEventoWithId(id);

        if(dados.getBytes().length > 2){
            String[] dadoSplit = dados.split(Evento.getSeparador());
            if(dadoSplit.length > 2){
                dadoSplit[3] = dadoSplit[3].replace("\\n", "\n");
                parent.getDatePicker().setValue(LocalDate.parse(dadoSplit[1]));
                parent.getHora().setText(dadoSplit[2]);
                parent.getEvento().setText(dadoSplit[3]);
            }
        }
    }
}
