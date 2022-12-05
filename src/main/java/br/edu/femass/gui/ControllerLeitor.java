package br.edu.femass.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

public class ControllerLeitor implements Initializable {

    private String telaNova;

    @FXML
    public void abrirTelaProfessor(ActionEvent e) {
        try {
            new GuiProfessor().iniciar(telaNova);
            GuiLeitor.fecharTela();
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    @FXML
    public void abrirTelaAluno(ActionEvent e) {
        try {
            new GuiAluno().iniciar(telaNova);
            GuiLeitor.fecharTela();
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    @FXML
    public void voltarTela(ActionEvent e) {
        try {
            if (telaNova.equals("Principal")) {
                new GuiPrincipal().iniciar();
                GuiLeitor.fecharTela();
            } else {
                if (telaNova.equals("Emprestimo")) {
                    // GuiEmprestimo telaEmprestimo = new GuiEmprestimo();
                    // GuiLeitor.fecharTela();
                } else {
                    chamadaErro("Erro ao carregar a tela anterior.");
                }
            }
        } catch (Exception ex) {
            chamadaErro("Erro ao carregar a tela anterior.");
        }
    }

    private void chamadaErro(String erro) {
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle("Alerta");
        dialogoInfo.setContentText(erro);
        dialogoInfo.showAndWait();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        telaNova = arg1.getBaseBundleName();
    }

}
