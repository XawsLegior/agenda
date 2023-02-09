package com.worrigan.agenda.eventos.funcoes;

import com.worrigan.agenda.MainController;
import com.worrigan.agenda.eventos.Evento;
import com.worrigan.agenda.eventos.EventoController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Editar {
    private static int id;

    public static void carregar(int id, EventoController parent) throws IOException {
        Editar.id = id;
        String dados = Evento.getEventoWithId(id);
        String[] dadoSplit = dados.split(Evento.getSeparador());
        parent.datePicker.setValue(LocalDate.parse(dadoSplit[1]));
        parent.hora.setText(dadoSplit[2]);
        parent.evento.setText(dadoSplit[3]);
    }

    public static void salvar(EventoController parent, MainController mainParent) throws IOException {
        LocalDate data = parent.datePicker.getValue();
        String hora = parent.hora.getText();
        String evento = parent.evento.getText();
        List<String> dados = Files.readAllLines(new File("eventos/eventos.ini").toPath());
        String separador = Evento.getSeparador();
        FileWriter newf = new FileWriter("eventos/eventos.ini");
        for(String dado: dados){
            if(dado.startsWith(String.valueOf(id))){
                dado = id + separador + data + separador + hora + separador + evento;
            }
            newf.write(dado);
            newf.write("\n");
        }
        newf.close();
        mainParent.getTableEvento().getItems().set(id, new Evento(String.valueOf(id), String.valueOf(data), hora, evento));
    }
}
