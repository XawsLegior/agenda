package com.worrigan.agenda.eventos;

import com.worrigan.agenda.MainController;
import com.worrigan.agenda.eventos.funcoes.Expirado;
import com.worrigan.agenda.eventos.warnings.WarningApplication;
import com.worrigan.agenda.eventos.warnings.WarningController;
import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class VerificarHorariosEvent {
    public static Boolean rodando = true;
    private static MainController parent;

    public static void eventos(MainController main){
        parent = main;
        new Thread(()->{
            while(rodando){
                try {
                    verificarHorarios();
                }
                catch(Exception ignored){}
            }
        }).start();
    }

    /* VERIFICAR HORARIOS */
    private static void verificarHorarios() throws InterruptedException {
        try {
            ObservableList<Evento> itens = parent.tableEvento.getItems();
            for (Evento item : itens) {
                String id = item.getId();
                long timeAtual = System.currentTimeMillis();
                long timeEvento = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(formatTime(item)).getTime();

                long diferencaMS = diferenca(timeEvento, timeAtual);
                String ultimoAviso = Warning.getWarning(id);
                long diferencaUltimoAviso = diferenca(timeAtual, ultimoAviso);
                String aviso;

                Object expirou = expirado(item.getData());
                if (diferencaMS > 1 && diferencaMS <= 3600000 && diferencaUltimoAviso >= 0 && expirou.toString().equals("false")) {
                    /* AVISO DE 1H */
                    if (diferencaMS >= 3480000 && diferencaUltimoAviso == 0) {
                        Warning.setWarnings(id, String.valueOf(timeAtual));
                        aviso = "Falta 1 hora para o inicio do evento: " + item.getEvento();
                        callWarning(aviso);
                    }
                    /* AVISO DE 30M */
                    else if (diferencaMS >= 1680000 && diferencaMS <= 1800000 && (diferencaUltimoAviso >= 1800000 || diferencaUltimoAviso == 0)) {
                        Warning.setWarnings(id, String.valueOf(timeAtual));
                        aviso = "Faltam 30m hora para o inicio do evento: " + item.getEvento();
                        callWarning(aviso);
                    }
                    /* AVISO DE 2M */
                    else if (diferencaMS <= 120000  && (diferencaUltimoAviso >= 120000 || diferencaUltimoAviso == 0)) {
                        aviso = "Evento está prestes a começar: " + item.getEvento();
                        Warning.setWarnings(id, String.valueOf(timeAtual));
                        callWarning(aviso);
                    }
                }
                else{
                    if(expirou.toString().equals("futuro")){
                        continue;
                    }
                    /* EVENTO INICIADO */
                    Warning.remove(id);
                    Expirado.expirado(id, parent);
                }

            }
        }
        catch (Exception ignored){}
        Thread.sleep(5000);
    }

    /* VERIFICAR SE JÁ PASSOU A DATA OU É ATUAL */
    public static Object expirado(String dataEvento){
        String[] evento = dataEvento.split("-");
        int ano = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
        int mes = Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
        int dia = Integer.parseInt(new SimpleDateFormat("dd").format(new Date()));

        // ANO EXPIRADO
        if(Integer.parseInt(evento[0]) < ano){
            return true;
        }
        // MES EXPIRADO
        if(Integer.parseInt(evento[1]) < mes){
            return true;
        }
        // MES ATUAL, MAS DIA EXPIRADO
        if(Integer.parseInt(evento[1]) == mes && Integer.parseInt(evento[2]) < dia){
            return true;
        }
        // EVENTO FUTURO
        if(Integer.parseInt(evento[0]) > ano || Integer.parseInt(evento[1]) > mes || Integer.parseInt(evento[2]) > dia){
            return "futuro";
        }
        return false;
    }

    /* MOSTRAR JANELA DE AVISO
     ABAIXO, NA DIREITA DA TELA
     */
    private static void callWarning(String aviso) throws InterruptedException {
        Platform.runLater(() ->{
            try {
                WarningController.setAviso(aviso);
                WarningApplication.show();
            }
            catch (Exception ignored){}
        });
        Thread.sleep(5000);
        Platform.runLater(WarningApplication::close);
    }

    /* TRANSFORMAR HORÁRIO EM TIMESTAMP */
    private static String formatTime(Evento item){
        String data = item.getData();
        String[] hora = item.getHora().split(":");
        String horaFormat;
        /* TRATAR HORA */
        try{
            horaFormat = hora[0] + ":" + hora[1] + ":" + hora[2];
        }
        catch (Exception ignored){
            horaFormat = hora[0] + ":" + hora[1] + ":00";
        }
        //yyyy-MM-dd hh:mm:ss
        return String.format("%s %s", data, horaFormat);
    }

    /* DIFERENÇA ENTRE OS TIMESTAMPS */
    private static long diferenca(Object time1, Object time2){
        try {
            String t1 = String.valueOf(time1);
            String t2 = String.valueOf(time2);
            return Long.parseLong(t1) - Long.parseLong(t2);
        }
        catch (Exception ignored){}
        return 0;
    }
}
