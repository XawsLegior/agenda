package com.worrigan.agenda;

import com.worrigan.agenda.contatos.Contato;
import com.worrigan.agenda.contatos.ContatoApplication;
import com.worrigan.agenda.contatos.ContatoController;
import com.worrigan.agenda.contatos.funcoes.Editar;
import com.worrigan.agenda.contatos.funcoes.Pesquisa;
import com.worrigan.agenda.contatos.funcoes.Visualizar;
import com.worrigan.agenda.eventos.Evento;
import com.worrigan.agenda.eventos.EventoApplication;
import com.worrigan.agenda.eventos.EventoController;
import com.worrigan.agenda.eventos.VerificarHorariosEvent;
import com.worrigan.agenda.eventos.funcoes.Carregar;
import com.worrigan.agenda.eventos.funcoes.Remover;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainController {
    /* EVENTOS */
    private static EventoController eventoControl;
    private static ContatoController contatoControl;

    @FXML private TabPane tabPane;
    @FXML private Tab tabEventos;
    /* EVENTOS */
    @FXML private TableView<Evento> tableEvento;
    @FXML private TableColumn<String, String> id;
    @FXML private TableColumn<String, String> data;
    @FXML private TableColumn<String, String> hora;
    @FXML private TableColumn<String, String> evento;
    /* EXPIRADOS */
    @FXML private TableView<Evento> tableEvento2;
    @FXML private TableColumn<String, String> id2;
    @FXML private TableColumn<String, String> data2;
    @FXML private TableColumn<String, String> hora2;
    @FXML private TableColumn<String, String> evento2;
    @FXML private Button btnZerar;

    /* CONTATOS */
    @FXML private TableView<Contato> tableContato;
    @FXML private TableColumn<String, String> nomeContato;
    @FXML private TableColumn<String, String> telefoneContato;
    @FXML private TableColumn<String, String> emailContato;
    @FXML private TableColumn<String, String> anotContato;
    @FXML private ComboBox<String> search;

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
        com.worrigan.agenda.contatos.funcoes.Remover.remover(this);
    }
    @FXML
    protected void onVisualizarContato() throws IOException {
        if(tableContato.selectionModelProperty().get().getSelectedIndex() == -1){return;}
        ContatoApplication.show();
        Visualizar.visualizar(contatoControl, this);
    }
    @FXML
    protected void onPesquisa() throws IOException {
        Pesquisa.pesquisa(this);
    }
    @FXML
    protected void onPesquisaSelect() throws IOException {
        String itemSelecionado = search.getSelectionModel().getSelectedItem();
        Pesquisa.pesquisaSelect(itemSelecionado, this);
    }

    /* ******************** GETTERS ********************
    */

    public TabPane getTabPane() {
        return tabPane;
    }

    public Tab getTabEventos() {
        return tabEventos;
    }

    public TableView<Evento> getTableEvento() {
        return tableEvento;
    }

    public TableColumn<String, String> getId() {
        return id;
    }

    public TableColumn<String, String> getData() {
        return data;
    }

    public TableColumn<String, String> getHora() {
        return hora;
    }

    public TableColumn<String, String> getEvento() {
        return evento;
    }

    public TableView<Evento> getTableEvento2() {
        return tableEvento2;
    }

    public TableColumn<String, String> getId2() {
        return id2;
    }

    public TableColumn<String, String> getData2() {
        return data2;
    }

    public TableColumn<String, String> getHora2() {
        return hora2;
    }

    public TableColumn<String, String> getEvento2() {
        return evento2;
    }

    public Button getBtnZerar() {
        return btnZerar;
    }

    public TableView<Contato> getTableContato() {
        return tableContato;
    }

    public TableColumn<String, String> getNomeContato() {
        return nomeContato;
    }

    public TableColumn<String, String> getTelefoneContato() {
        return telefoneContato;
    }

    public TableColumn<String, String> getEmailContato() {
        return emailContato;
    }

    public TableColumn<String, String> getAnotContato() {
        return anotContato;
    }

    public ComboBox<String> getSearch() {
        return search;
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