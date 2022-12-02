package br.edu.femass.gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAutor;
import br.edu.femass.model.Autor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ControllerAutor implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtSobrenome;
    @FXML
    private TextField txtNacionalidade;
    @FXML
    private TableView<Autor> tabAutores = new TableView<Autor>();
    @FXML
    private TableColumn<Autor, Long> colId = new TableColumn<>();
    @FXML
    private TableColumn<Autor, String> colNome = new TableColumn<>();
    @FXML
    private TableColumn<Autor, String> colSobrenome = new TableColumn<>();
    @FXML
    private TableColumn<Autor, String> colNacionalidade = new TableColumn<>();
    @FXML
    private TextField txtId;

    private String telaAnterior;
    DaoAutor daoAutor = new DaoAutor();

    @FXML
    public void cadastrarAutor(ActionEvent e) {
        try {
            daoAutor.adicionar(new Autor(txtNome.getText(), txtSobrenome.getText(), txtNacionalidade.getText()));
            atualizarTabela();
        } catch (Exception ex) {
            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Alerta");
            dialogoInfo.setContentText(ex.getMessage());
            dialogoInfo.showAndWait();
        }
    }

    @FXML
    public void voltarTela(ActionEvent e) {
        if (telaAnterior.equals("Principal")) {
            GuiPrincipal.main(null);
            
        } else {
            if (telaAnterior.equals("Livro")) {
                //GuiLivro telaLivro = new GuiLivro();
            } 
            else {
                Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                dialogoInfo.setTitle("Alerta");
                dialogoInfo.setContentText("Erro ao carregar a tela anterior.");
                dialogoInfo.showAndWait();
            }
        }
    }

    @FXML
    public void selecionarLinhaTeclado(KeyEvent e) {
        exibirDados();
    }

    @FXML
    public void selecionarLinhaMouse(MouseEvent e) {
        exibirDados();
    }

    private void atualizarTabela() throws Exception {
        List<Autor> autores = daoAutor.listarTodos();

        ObservableList<Autor> dados = FXCollections.observableArrayList(autores);
        tabAutores.setItems(dados);
    }

    private void exibirDados(){
        Autor autor = tabAutores.getSelectionModel().getSelectedItem();

        if (autor == null) return;

        txtId.setText(autor.getId().toString());
        txtNome.setText(autor.getNome());
        txtSobrenome.setText(autor.getSobrenome());
        txtNacionalidade.setText(autor.getNacionalidade());
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        telaAnterior = arg1.getBaseBundleName();

        try {
            atualizarTabela();
        } catch (Exception ex) {
            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Alerta");
            dialogoInfo.setContentText(ex.getMessage());
            dialogoInfo.showAndWait();
        }
        
    }
}