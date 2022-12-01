package com.worrigan.agenda;

import com.worrigan.agenda.contatos.Contato;
import com.worrigan.agenda.contatos.ContatoApplication;
import com.worrigan.agenda.contatos.ContatoController;
import com.worrigan.agenda.contatos.funcoes.Editar;
import com.worrigan.agenda.contatos.funcoes.Visualizar;
import com.worrigan.agenda.eventos.Evento;
import com.worrigan.agenda.eventos.EventoApplication;
import com.worrigan.agenda.eventos.EventoController;
import com.worrigan.agenda.eventos.VerificarHorariosEvent;
import com.worrigan.agenda.eventos.funcoes.Carregar;
import com.worrigan.agenda.eventos.funcoes.Remover;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.FileWriter;
import java.io.IOException;


public class MainController {
    /* EVENTOS */
    private static EventoController eventoControl;
    private static ContatoController contatoControl;

    @FXML private Button btnAdd;
    @FXML TabPane tabPane;
    @FXML Tab tabEventos;
    /* EVENTOS */
    @FXML public TableView<Evento> tableEvento;
    @FXML public TableColumn<String, String> id;
    @FXML public TableColumn<String, String> data;
    @FXML public TableColumn<String, String> hora;
    @FXML public TableColumn<String, String> evento;
    /* EXPIRADOS */
    @FXML public TableView<Evento> tableEvento2;
    @FXML public TableColumn<String, String> id2;
    @FXML public TableColumn<String, String> data2;
    @FXML public TableColumn<String, String> hora2;
    @FXML public TableColumn<String, String> evento2;
    @FXML public Button btnZerar;

    /* CONTATOS */
    @FXML public TableView<Contato> tableContato;
    @FXML public TableColumn<String, String> nomeContato;
    @FXML public TableColumn<String, String> telefoneContato;
    @FXML public TableColumn<String, String> emailContato;
    @FXML public TableColumn<String, String> anotContato;

    public static void setEventoControl(EventoController controller){
        eventoControl = controller;
    }
    public static void setContatoControl(ContatoController controller){
        contatoControl = controller;
    }

    /* ******************** EVENTOS ********************
    */
    /* ADICIONAR EVENTO */
    @FXML
    protected void onAdicionarEvento() throws IOException {
        EventoApplication.show();
        eventoControl.setTitle("Adicionar");
    }
    /* EDITAR EVENTO */
    @FXML
    protected void onEditarEvento() throws IOException {
        if(tableEvento.selectionModelProperty().get().getSelectedIndex() == -1){return;}
        EventoApplication.show();
        eventoControl.setTitle("Editar");
        eventoControl.editar(tableEvento.getSelectionModel().getSelectedIndex());
    }

    /* REMOVER EVENTO */
    @FXML
    protected void onRemoverEvento() throws IOException {
        if(tableEvento.selectionModelProperty().get().getSelectedIndex() == -1){return;}
        int selected = tableEvento.getSelectionModel().getSelectedIndex();
        Remover.remover(selected, this);
    }

    /* VISUALIZAR EVENTO */
    @FXML
    protected void onVisualizarEvento() throws IOException {
        if(tableEvento.selectionModelProperty().get().getSelectedIndex() == -1){return;}
        EventoApplication.show();
        eventoControl.setTitle("Evento");
        int selected = tableEvento.getSelectionModel().getSelectedIndex();
        eventoControl.visualizar(selected);
    }

    public void onZerarExpirado() throws IOException {
        tableEvento2.getItems().clear();
        new FileWriter("eventos/expirados.ini").close();
        btnZerar.setDisable(true);
    }

    /* ******************** CONTATOS ********************
    */
    @FXML
    protected void onAdicionarContato() throws IOException {
        ContatoApplication.show();
        ContatoController.area = "adicionar";
    }
    @FXML
    protected void onEditarContato() throws IOException {
        if(tableContato.selectionModelProperty().get().getSelectedIndex() == -1){return;}
        ContatoApplication.show();
        Editar.carregar(tableContato.selectionModelProperty().get().getSelectedIndex(), contatoControl, this);
        ContatoController.area = "editar";
    }
    @FXML
    protected void onRemoverContato() throws IOException {
        if(tableContato.selectionModelProperty().get().getSelectedIndex() == -1){return;}
        com.worrigan.agenda.contatos.funcoes.Remover.remover(tableContato.selectionModelProperty().get().getSelectedIndex(), this);
    }
    @FXML
    protected void onVisualizarContato() throws IOException {
        if(tableContato.selectionModelProperty().get().getSelectedIndex() == -1){return;}
        ContatoApplication.show();
        Visualizar.visualizar(contatoControl, this);
    }

    public void initialize() throws IOException {
        /* INICIAR THREAD QUE VAI VERIFICAR EVENTOS */
        VerificarHorariosEvent.eventos(this);

        /* SETAR CONTROLLERS */
        EventoController.setController(this);
        ResizeEvent.setController(this);
        ContatoController.setController(this);

        /* CARREGAR EVENTOS & MOSTRAR NA LISTVIEW */
        Carregar.carregar(this);
        /* CARREGAR EVENTOS EXPIRADOS & MOSTRAR NA LISTVIEW */
        Carregar.expirados(this);
        /* CARREGAR CONTATOS & MOSTRAR NA LISTVIEW */
        com.worrigan.agenda.contatos.funcoes.Carregar.contatos(this);
    }
}