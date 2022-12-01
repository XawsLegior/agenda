package com.worrigan.agenda.contatos;

import com.worrigan.agenda.MainController;
import com.worrigan.agenda.contatos.funcoes.Editar;
import com.worrigan.agenda.contatos.funcoes.Salvar;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;


public class ContatoController {
    private static MainController main;
    public static String area = "";

    @FXML public TextField nomeContato;
    @FXML public TextField telefoneContato;
    @FXML public TextField emailContato;
    @FXML public TextArea anotContato;
    @FXML public Button btnSalvar;
    @FXML public Label contatoLabel;
    /* SALVAR CONTATO */
    public void salvar() throws IOException {
        if(area.equals("adicionar")){
            Salvar.salvar(this, main);
        }
        else if(area.equals("editar")){
            Editar.salvar(this, main);
        }
    }

    public static void setController(MainController self){
        main = self;
    }

    public void initialize(){
        MainController.setContatoControl(this);
    }
}
