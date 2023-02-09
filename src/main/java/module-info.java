module com.worrigan.agenda {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;

    opens com.worrigan.agenda to javafx.fxml;
    opens com.worrigan.agenda.contatos to javafx.fxml;
    opens com.worrigan.agenda.eventos to javafx.fxml;

    exports com.worrigan.agenda;
    exports com.worrigan.agenda.eventos;
    exports com.worrigan.agenda.contatos;
    exports com.worrigan.agenda.eventos.warnings;
}