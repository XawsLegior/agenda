package com.worrigan.agenda.eventos;

import com.worrigan.agenda.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class EventoApplication {
    private static Stage stage;
    public static void show() throws IOException {
        FXMLLoader fxml = new FXMLLoader(MainApplication.class.getResource("/com/worrigan/agenda/eventos/window.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(fxml.load());
        stage.setScene(scene);
        stage.setTitle("Evento");
        stage.setResizable(false);
        stage.getIcons().add(new Image(MainApplication.class.getResource("/com/worrigan/agenda/icone.png").openStream()));
        EventoApplication.stage = stage;
        stage.show();
    }

    public static void close() {
        stage.close();
    }
}
