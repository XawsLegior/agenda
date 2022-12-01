package com.worrigan.agenda;

import com.worrigan.agenda.eventos.VerificarHorariosEvent;
import javafx.application.Platform;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.net.URL;

class Bandeja{
    public static void tray(Stage main) throws AWTException {

        try {
            java.awt.Toolkit.getDefaultToolkit();
            // app requires system tray support, just exit if there is no support.
            if (!java.awt.SystemTray.isSupported()) {
                Platform.exit();
            }

            // ICONE.
            java.awt.SystemTray tray = java.awt.SystemTray.getSystemTray();
            URL imageLoc = new URL(String.valueOf(MainApplication.class.getResource("/com/worrigan/agenda/iconeTray.png")));
            java.awt.Image image = ImageIO.read(imageLoc);
            //Image image = Toolkit.getDefaultToolkit().getImage(imageLoc);
            java.awt.TrayIcon trayIcon = new java.awt.TrayIcon(image);

            // DUPLO CLICK NO ICONE
            trayIcon.addActionListener(event -> Platform.runLater(()->{
                Bandeja.showStage(main);
                tray.remove(trayIcon);
            }));

            // CLICK MOSTRAR
            java.awt.MenuItem openItem = new java.awt.MenuItem("Abrir");
            openItem.addActionListener(event -> Platform.runLater(()->{
                Bandeja.showStage(main);
                tray.remove(trayIcon);
            }));

            // CLICK FECHAR
            java.awt.MenuItem exitItem = new java.awt.MenuItem("Fechar");
            exitItem.addActionListener(event -> {
                Platform.exit();
                VerificarHorariosEvent.rodando = false;
                tray.remove(trayIcon);
            });

            // setup the popup menu for the application.
            final java.awt.PopupMenu popup = new java.awt.PopupMenu();
            popup.add(openItem);
            popup.addSeparator();
            popup.add(exitItem);
            trayIcon.setPopupMenu(popup);
            // add the application tray icon to the system tray.
            tray.add(trayIcon);
        }
        catch (Exception ignored){}
    }

    private static Runnable showStage(Stage main) {
        main.show();
        main.toFront();
        return null;
    }

}