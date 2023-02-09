package com.worrigan.agenda.eventos.funcoes;

import com.worrigan.agenda.eventos.Evento;
import com.worrigan.agenda.eventos.EventoController;
import javafx.application.Platform;
import javafx.scene.paint.Color;

import java.io.FileWriter;
import java.io.IOException;

import static com.worrigan.agenda.eventos.EventoController.mainController;

public class Salvar {
    public static void salvar(EventoController parent) throws IOException {
        String dataV = String.valueOf(parent.getDatePicker().getValue());
        String horaV = parent.getHora().getText();
        String eventoV = parent.getEvento().getText();
        String separador = Evento.getSeparador();

        /* VERIFICAR VALORES VAZIOS*/
        if(dataV.getBytes().length < 10){
            parent.getTitle().setText("Data não pode ficar em branco!");
            parent.getTitle().setStyle("-fx-text-fill: yellow");
            return;
        }
        else if(!horaV.contains(":")){
            parent.getTitle().setText("Formato de hora inválido! hh:mm - Exemplo 12:45");
            parent.getTitle().setStyle("-fx-text-fill: yellow");
            return;
        }
        else if(eventoV.contains(separador)){
            parent.getTitle().setText(String.format("Não informe %s no evento!", separador));
            parent.getTitle().setStyle("-fx-text-fill: yellow");
            return;
        }
        else if(eventoV.isEmpty()){
            parent.getTitle().setText(String.format("Evento não pode ficar em branco!"));
            parent.getTitle().setStyle("-fx-text-fill: yellow");
            return;
        }

        /* CRIAR ARQUIVO E INSERIR EVENTOS */
        String index = String.valueOf(mainController.getTableEvento().getItems().size());
        FileWriter f = new FileWriter("eventos/eventos.ini", true);
        String event = String.format("%s%s%s%s%s%s%s", index, separador, dataV, separador, horaV, separador, eventoV);
        event = event.replace("\n", "\\n");
        f.write(event);
        f.write("\n");
        f.close();
        mainController.getTableEvento().getItems().add(new Evento(index, dataV, horaV, eventoV));
        String eventoAtual = parent.getTitle().getText();
        parent.getTitle().setText("Evento adicionado!");
        parent.getTitle().setTextFill(Color.WHITE);
        new Thread(()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException Ignored) {}
            Platform.runLater(()-> {
                parent.getTitle().setText(eventoAtual);
                parent.getTitle().setTextFill(Color.LIME);
            });
        }).start();
    }
}
