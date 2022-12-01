package com.worrigan.agenda.eventos.warnings;

import com.worrigan.agenda.MainApplication;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.media.AudioClip;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URISyntaxException;

public class WarningApplication extends WarningController{
    private static Stage stageWarn;

    @FXML
    public static void show() throws IOException, URISyntaxException {
        FXMLLoader fxml = new FXMLLoader(MainApplication.class.getResource("/com/worrigan/agenda/warningWindow.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxml.load());
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        double x = Screen.getPrimary().getBounds().getWidth() - 405;
        double y = Screen.getPrimary().getBounds().getHeight() - 300;
        stage.setX(x);
        stage.setY(y);
        tocarEfeito();
        stage.setAlwaysOnTop(true);
        stageWarn = stage;
        stage.show();
    }

    public static void close(){
        stageWarn.close();
    }

    private static void tocarEfeito() throws URISyntaxException {
        String url = MainApplication.class.getResource("/com/worrigan/agenda/warningSonoro.mp3").toURI().toString();
        AudioClip clip = new AudioClip(url);
        clip.play();
    }
}
