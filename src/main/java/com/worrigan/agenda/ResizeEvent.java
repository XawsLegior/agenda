package com.worrigan.agenda;

import javafx.beans.value.ChangeListener;
import javafx.stage.Stage;

public class ResizeEvent{
    private static MainController controller;
    private static double posX = 0;
    private static double posY = 0;

    /* WIDTH */
    public static ChangeListener<? super Number> resizeWidth(Stage stage){
        ChangeListener<? super Number> resizeEvent = (ChangeListener<Number>) (observable, oldValue, newValue) -> setResizeElements(stage.getWidth(), stage.getHeight());
        return resizeEvent;
    }

    /* HEIGHT */
    public static ChangeListener<? super Number> resizeHeight(Stage stage){
        ChangeListener<? super Number> resizeEvent = (ChangeListener<Number>) (observable, oldValue, newValue) -> setResizeElements(stage.getWidth(), stage.getHeight());
        return resizeEvent;
    }

    public static void setController(MainController control){
        controller = control;
    }

    private static void setResizeElements(Double width, Double height){
        controller.getTabPane().setPrefSize(width, height);
    }
}
