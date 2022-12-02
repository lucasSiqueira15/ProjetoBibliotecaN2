package br.edu.femass.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;


public class ControllerPrincipal implements Initializable {

    @FXML
    private Button btnCadAutores;
    @FXML
    private Button btnCadLivro;
    @FXML
    private Button btnCadLeitor;
    @FXML
    private ComboBox<String> cboUsuario;
    @FXML
    private Label lblBibliotecario;
    @FXML
    private Label lblAtendente;
    @FXML
    private Button btnRelAtraso;
    @FXML
    private Button btnRealizarEmp;
    @FXML
    private Button btnDevolverEmp;

    private static final String funcionario0 = "";
    private static final String funcionario1 = "Bibliotecario";
    private static final String funcionario2 = "Atendente";

    @FXML
    private void telaAutor(ActionEvent e) {
        try {
           new GuiAutor().iniciar(ControllerPrincipal.toNomeTela());;
        } catch (Exception ex) {
            Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
            dialogoInfo.setTitle("Alerta");
            dialogoInfo.setContentText(ex.getMessage());
            dialogoInfo.showAndWait();
        }
    }

    @FXML
    private void telaLivro(ActionEvent e) {
        //Acessar Tela de Livro
    }

    @FXML
    private void telaRelatorio(ActionEvent e) {
        //Acessar Tela de Relatorio
    }

    @FXML
    private void telaLeitor(ActionEvent e) {
        //Acessar Tela de Leitor
    }

    @FXML
    private void telaRealizarEmp(ActionEvent e) {
        //Acessar Tela de Realizar emprestimo
    }

    @FXML
    private void telaDevolverEmp(ActionEvent e) {
        //Acessar Tela de Devolver emprestimo
    }

    @FXML
    private void selecionarUsuario(ActionEvent e) {
        if (cboUsuario.getSelectionModel().getSelectedItem().toString().equals(funcionario1)) {
            lblAtendente.setVisible(false);
            btnCadLeitor.setVisible(false);
            btnRealizarEmp.setVisible(false);
            btnDevolverEmp.setVisible(false);

            lblBibliotecario.setVisible(true);
            btnCadLivro.setVisible(true);
            btnRelAtraso.setVisible(true);
            btnCadAutores.setVisible(true);
        } else {
            if (cboUsuario.getSelectionModel().getSelectedItem().toString().equals(funcionario2)) {
                lblAtendente.setVisible(true);
                btnCadLeitor.setVisible(true);
                btnRealizarEmp.setVisible(true);
                btnDevolverEmp.setVisible(true);

                lblBibliotecario.setVisible(false);
                btnCadLivro.setVisible(false);
                btnRelAtraso.setVisible(false);
                btnCadAutores.setVisible(false);
            } else {
                if (cboUsuario.getSelectionModel().getSelectedItem().toString().equals(funcionario0)) {
                    lblAtendente.setVisible(false);
                    btnCadLeitor.setVisible(false);
                    btnRealizarEmp.setVisible(false);
                    btnDevolverEmp.setVisible(false);

                    lblBibliotecario.setVisible(false);
                    btnCadLivro.setVisible(false);
                    btnRelAtraso.setVisible(false);
                    btnCadAutores.setVisible(false);
                } else {
                    Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                    dialogoInfo.setTitle("Alerta");
                    dialogoInfo.setContentText("Erro ao carregar as opções de Menu.");
                    dialogoInfo.showAndWait();
                }
            }
        }
    }

    private void preencherCombo(){
        ObservableList<String> itens = FXCollections.observableArrayList(funcionario0, funcionario1, funcionario2);
        cboUsuario.setItems(itens);
        cboUsuario.getSelectionModel().selectFirst();
        selecionarUsuario(new ActionEvent());
    }

    public static String toNomeTela() {
        return "Principal";
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherCombo();
    }
}
