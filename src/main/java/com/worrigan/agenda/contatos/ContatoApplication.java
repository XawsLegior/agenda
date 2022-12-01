package com.worrigan.agenda.contatos;

import com.worrigan.agenda.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ContatoApplication {
    private static Stage stage;

    public static void show() throws IOException {
        FXMLLoader fxml = new FXMLLoader(MainApplication.class.getResource("/com/worrigan/agenda/contatos/window.fxml"));
        Scene scene = new Scene(fxml.load());
        Stage stage = new Stage();
        ContatoApplication.stage = stage;
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Contato");
        stage.getIcons().add(new Image(MainApplication.class.getResource("/com/worrigan/agenda/icone.png").openStream()));
        stage.show();
    }

    public static void close(){
        stage.close();
    }
}
