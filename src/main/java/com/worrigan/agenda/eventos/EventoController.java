package com.worrigan.agenda.eventos;

import com.worrigan.agenda.MainController;
import com.worrigan.agenda.eventos.funcoes.Editar;
import com.worrigan.agenda.eventos.funcoes.Salvar;
import com.worrigan.agenda.eventos.funcoes.Visualizar;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;

public class EventoController {
    public static MainController mainController;
    private String area;

    @FXML public DatePicker datePicker;
    @FXML public Label title;
    @FXML public TextField hora;
    @FXML public TextArea evento;
    @FXML public Button btnSalvar;

    public static void setController(MainController main){
        mainController = main;
    }

    public void setTitle(String titulo){
        title.setText(titulo);
    }

    public void editar(int id) throws IOException {
        Editar.carregar(id, this);
    }

    /* VISUALIZAR EVENTO */
    public void visualizar(int id) throws IOException {
        Visualizar.visualizar(id, this);
    }

    /* SALVAR */
    public void salvar() throws IOException {
        if(area == null){
            area = title.getText().toLowerCase();
        }
        if(area.equals("adicionar")){
            Salvar.salvar(this);
        }
        else if(area.equals("editar")){
            Editar.salvar(this, mainController);
            EventoApplication.close();
        }
    }

    public void initialize(){
        MainController.setEventoControl(this);
    }

}
