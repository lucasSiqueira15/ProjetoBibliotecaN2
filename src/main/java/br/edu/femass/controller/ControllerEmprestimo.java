package br.edu.femass.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.dao.DaoEmprestimo;
import br.edu.femass.dao.DaoExemplar;
import br.edu.femass.dao.DaoProfessor;
import br.edu.femass.gui.GuiEmprestimo;
import br.edu.femass.gui.GuiLeitor;
import br.edu.femass.gui.GuiPrincipal;
import br.edu.femass.model.Exemplar;
import br.edu.femass.model.Leitor;
import br.edu.femass.model.Professor;
import br.edu.femass.model.Aluno;
import br.edu.femass.model.Emprestimo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ControllerEmprestimo implements Initializable {

    @FXML
    private Button btnProcessar;
    @FXML
    private Button btnVoltarTela;
    @FXML
    private Button btnNovoLeitor;

    @FXML
    private ComboBox<Exemplar> cboExemplar;
    @FXML
    private ComboBox cboLeitor;

    @FXML
    private DatePicker txtDataEmprestimo;
    @FXML
    private DatePicker txtDataPrevDevolucao;

    @FXML
    private CheckBox chbProfessor;
    @FXML
    private CheckBox chbAluno;

    private DaoEmprestimo daoEmprestimo = new DaoEmprestimo();
    private DaoExemplar daoExemplar = new DaoExemplar();
    private DaoAluno daoAluno = new DaoAluno();
    private DaoProfessor daoProfessor = new DaoProfessor();

    // Método de inicialização-----------------------------------

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            atualizarComboExemplares();
            chbAluno.setSelected(false);
            chbProfessor.setSelected(false);
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    // Métodos de Eventos ----------------------------------------

    @FXML
    public void processarEmprestimo(ActionEvent e) {
        try {
            Emprestimo emprestimo = new Emprestimo(cboExemplar.getSelectionModel().getSelectedItem(), (Leitor) cboLeitor.getSelectionModel().getSelectedItem());

            daoExemplar.modificar(emprestimo.getExemplar());
            daoEmprestimo.adicionar(emprestimo);
            
            limparDados();
            chamadaSucesso();
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    @FXML
    public void marcarAluno(MouseEvent e) {
        try {
            if (chbAluno.isSelected() == true) {
                atualizarComboAluno();
                chbProfessor.setSelected(false);
            } else {
                limparComboLeitor();
            }
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    @FXML
    public void marcarProfessor(MouseEvent e) {
        try {
            if (chbProfessor.isSelected() == true) {
                atualizarComboProfessor();
                chbAluno.setSelected(false);
            } else {
                limparComboLeitor();
            }
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    @FXML
    public void selecionarLinhaTeclado(KeyEvent e) {
        try {
            preencherDatas();
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    @FXML
    public void selecionarLinhaMouse(MouseEvent e) {
        try {
            preencherDatas();
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    @FXML
    public void novoLeitor(ActionEvent e) {
        try {
            new GuiLeitor().iniciar(new ControllerEmprestimo().toString());
            GuiEmprestimo.fecharTela();
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    @FXML
    public void voltarTela(ActionEvent e) {
        try {
            new GuiPrincipal().iniciar();
            GuiEmprestimo.fecharTela();
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    // Métodos auxiliares ---------------------------------------

    public void preencherDatas() {
        if (chbAluno.isSelected()) {
            Aluno aluno = (Aluno) cboLeitor.getSelectionModel().getSelectedItem();
            if (aluno != null) {
                LocalDate previsaoDevolucao = LocalDate.now().plusDays(aluno.getPrazoMaximoDevolucao());

                txtDataEmprestimo.setValue(LocalDate.now());
                txtDataPrevDevolucao.setValue(previsaoDevolucao);
            }
        } else {
            if (chbProfessor.isSelected()) {
                Professor professor = (Professor) cboLeitor.getSelectionModel().getSelectedItem();
                if (professor != null) {
                    LocalDate previsaoDevolucao = LocalDate.now().plusDays(professor.getPrazoMaximoDevolucao());
                    
                    txtDataEmprestimo.setValue(LocalDate.now());
                    txtDataPrevDevolucao.setValue(previsaoDevolucao);
                }
            }
        }
    }

    private void atualizarComboProfessor() throws Exception {
        cboLeitor.getItems().clear();
        List<Professor> professores = daoProfessor.listarTodos();
        ObservableList<Professor> dados = FXCollections.observableArrayList(professores);
        cboLeitor.setItems(dados);
    }

    private void atualizarComboAluno() throws Exception {
        cboLeitor.getItems().clear();
        List<Aluno> alunos = daoAluno.listarTodos();
        ObservableList<Aluno> dados = FXCollections.observableArrayList(alunos);
        cboLeitor.setItems(dados);
    }

    private void atualizarComboExemplares() throws Exception {
        cboExemplar.getItems().clear();
        List<Exemplar> exemplares = daoExemplar.listarTodosDisponivel();
        ObservableList<Exemplar> dados = FXCollections.observableArrayList(exemplares);
        cboExemplar.setItems(dados);
    }

    private void limparComboLeitor() {
        cboLeitor.getItems().clear();
    }

    private void limparDados() throws Exception {
        cboLeitor.getItems().clear();
        chbProfessor.setSelected(false);
        chbAluno.setSelected(false);
        txtDataEmprestimo.setValue(null);
        txtDataPrevDevolucao.setValue(null);
        atualizarComboExemplares();
    }

    private void chamadaErro(String erro) {
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle("Alerta");
        dialogoInfo.setContentText(erro);
        dialogoInfo.showAndWait();
    }

    private void chamadaSucesso() {
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle("Alerta");
        dialogoInfo.setContentText("Empréstimo Realizado com Sucesso!");
        dialogoInfo.showAndWait();
    }

    @Override
    public String toString() {
        return "Emprestimo";
    }
}
