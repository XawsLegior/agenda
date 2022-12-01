package com.worrigan.agenda.eventos.funcoes;

import com.worrigan.agenda.eventos.Evento;
import com.worrigan.agenda.eventos.EventoController;

import java.io.IOException;
import java.time.LocalDate;

public class Visualizar {
    public static void visualizar(int id, EventoController parent) throws IOException {
        parent.btnSalvar.setVisible(false);
        parent.datePicker.setEditable(false);
        parent.datePicker.setOnMouseClicked(event -> parent.datePicker.hide());
        parent.hora.setEditable(false);
        parent.evento.setEditable(false);
        String dados = Evento.getEventoWithId(id);

        if(dados.getBytes().length > 2){
            String[] dadoSplit = dados.split(Evento.getSeparador());
            if(dadoSplit.length > 2){
                dadoSplit[3] = dadoSplit[3].replace("\\n", "\n");
                parent.datePicker.setValue(LocalDate.parse(dadoSplit[1]));
                parent.hora.setText(dadoSplit[2]);
                parent.evento.setText(dadoSplit[3]);
            }
        }
    }
}
