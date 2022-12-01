package com.worrigan.agenda;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.awt.*;
import java.io.IOException;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Agenda");
        stage.setScene(scene);
        stage.setMinWidth(800.0);
        stage.setMinHeight(500.0);
        stage.getIcons().add(new Image(MainApplication.class.getResource("/com/worrigan/agenda/icone.png").openStream()));

        stage.widthProperty().addListener(ResizeEvent.resizeWidth(stage));
        stage.heightProperty().addListener(ResizeEvent.resizeHeight(stage));
        stage.setOnCloseRequest(event ->{
            Platform.setImplicitExit(false);
            try {
                Bandeja.tray(stage);
            } catch (Exception ignored) {}
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

