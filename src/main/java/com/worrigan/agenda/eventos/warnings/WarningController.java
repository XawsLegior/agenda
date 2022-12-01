package com.worrigan.agenda.eventos.warnings;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class WarningController {
    @FXML public TextArea warningText;
    private static String aviso = "";

    public void initialize(){
        warningText.setText(aviso);
    }

    public static void setAviso(String warn){
        aviso = warn;
    }
}
