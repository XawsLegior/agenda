package com.worrigan.agenda.eventos;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;

public class Evento {
    private static final String separador = "/";
    private final String id;
    private final String data;
    private final String hora;
    private final String evento;

    public Evento(String id, String data, String hora, String evento) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.evento = evento;
    }

    public static String getSeparador() {
        return separador;
    }


    public String getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    /* PEGAR EVENTO REDUZIDO PARA MOSTRAR NA LISTVIEW*/
    public String getEvento() {
        String eventoEdit = evento;
        eventoEdit = eventoEdit.replace("\n", " ");
        if(eventoEdit.length() > 100){
            return eventoEdit.substring(0, 100);
        }
        return eventoEdit;
    }

    /* PEGAR EVENTO COMPLETO, PARA EDIÇÃO/VISUALIZAÇÃO */
    public static String getEventoWithId(int id) throws IOException {
        File ev = new File("eventos/eventos.ini");
        List<String> dados = Files.readAllLines(ev.toPath());
        String infos = "";
        for(String dado: dados){
            if(dado.startsWith(String.valueOf(id))){
                infos = dado;
                break;
            }
        }
        return infos;
    }

}

class Warning{
    private static final HashMap<String, String> warnings = new HashMap<>();

    /* ADICIONAR TIMESTAMP DO ULTIMO AVISO SOBRE O EVENTO */
    public static void setWarnings(String index, String valor){
        warnings.put(index, valor);
    }

    /* PEGAR TIMESTAMP DO ULTIMO AVISO SOBRE O EVENTO */
    public static String getWarning(String id){
        try {
            return warnings.get(id);
        }
        catch(Exception ignored){
            return null;
        }
    }

    /* REMOVER WARNING ATUAL */
    public static void remove(String id){
        warnings.remove(id);
    }
}
