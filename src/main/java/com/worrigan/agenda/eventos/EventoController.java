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

    @FXML private DatePicker datePicker;
    @FXML private Label title;
    @FXML private TextField hora;
    @FXML private TextArea evento;
    @FXML private Button btnSalvar;

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

    /* GETTERS */

    public DatePicker getDatePicker() {
        return datePicker;
    }

    public Label getTitle() {
        return title;
    }

    public TextField getHora() {
        return hora;
    }

    public TextArea getEvento() {
        return evento;
    }

    public Button getBtnSalvar() {
        return btnSalvar;
    }

    public void initialize(){
        MainController.setEventoControl(this);
    }

}
