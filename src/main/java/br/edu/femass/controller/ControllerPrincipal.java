package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.gui.GuiAutor;
import br.edu.femass.gui.GuiDevolver;
import br.edu.femass.gui.GuiEmprestimo;
import br.edu.femass.gui.GuiLeitor;
import br.edu.femass.gui.GuiLivro;
import br.edu.femass.gui.GuiPrincipal;
import br.edu.femass.gui.GuiRelatorio;
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
           new GuiAutor().iniciar(ControllerPrincipal.toNomeTela());
           GuiPrincipal.fecharTela();
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    @FXML
    private void telaLivro(ActionEvent e) {
        try {
            new GuiLivro().iniciar();
            GuiPrincipal.fecharTela();
         } catch (Exception ex) {
             chamadaErro(ex.getMessage());
         }
    }

    @FXML
    private void telaRelatorio(ActionEvent e) {
        try {
            new GuiRelatorio().iniciar();
            GuiPrincipal.fecharTela();
         } catch (Exception ex) {
             chamadaErro(ex.getMessage());
         }
    }

    @FXML
    private void telaLeitor(ActionEvent e) {
        try {
            new GuiLeitor().iniciar(ControllerPrincipal.toNomeTela());
            GuiPrincipal.fecharTela();
         } catch (Exception ex) {
             chamadaErro(ex.getMessage());
         }
    }

    @FXML
    private void telaRealizarEmp(ActionEvent e) {
        try {
            new GuiEmprestimo().iniciar();
            GuiPrincipal.fecharTela();
         } catch (Exception ex) {
             chamadaErro(ex.getMessage());
         }
    }

    @FXML
    private void telaDevolverEmp(ActionEvent e) {
        try {
            new GuiDevolver().iniciar();
            GuiPrincipal.fecharTela();
         } catch (Exception ex) {
             chamadaErro(ex.getMessage());
         }
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
                    chamadaErro("Erro ao carregar as op????es de Menu.");
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

    private void chamadaErro(String erro) {
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle("Alerta");
        dialogoInfo.setContentText(erro);
        dialogoInfo.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preencherCombo();
    }
}
