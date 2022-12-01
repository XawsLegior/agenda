package com.worrigan.agenda.contatos.funcoes;

import com.worrigan.agenda.MainController;
import com.worrigan.agenda.contatos.Contato;
import com.worrigan.agenda.contatos.ContatoController;

public class Visualizar {
    public static void visualizar(ContatoController parent, MainController main){
        int id = main.tableContato.selectionModelProperty().get().getSelectedIndex();
        Contato item = main.tableContato.getItems().get(id);
        parent.nomeContato.setText(item.getNome());
        parent.telefoneContato.setText(item.getTelefone());
        parent.emailContato.setText(item.getEmail());
        parent.anotContato.setText(item.getObservacao());

        parent.nomeContato.setEditable(false);
        parent.telefoneContato.setEditable(false);
        parent.emailContato.setEditable(false);
        parent.anotContato.setEditable(false);
        parent.btnSalvar.setVisible(false);
    }
}
