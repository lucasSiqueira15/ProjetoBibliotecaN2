package br.edu.femass.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoEmprestimo;
import br.edu.femass.gui.GuiPrincipal;
import br.edu.femass.gui.GuiRelatorio;
import br.edu.femass.model.Exemplar;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerRelatorio implements Initializable {

    @FXML
    private Button btnExtrair;
    @FXML
    private Button btnVoltarTela;
    @FXML
    private Button btnLimpar;

    @FXML
    private TableView<Emprestimo> tabEmprestimo = new TableView<Emprestimo>();
    @FXML
    private TableColumn<Emprestimo, Long> colId = new TableColumn<>();
    @FXML
    private TableColumn<Emprestimo, Professor> colLeitorProfessor = new TableColumn<>();
    @FXML
    private TableColumn<Emprestimo, Aluno> colLeitorAluno = new TableColumn<>();
    @FXML
    private TableColumn<Emprestimo, Exemplar> colExemplar = new TableColumn<>();
    @FXML
    private TableColumn<Emprestimo, LocalDate> colDataEmp = new TableColumn<>();
    @FXML
    private TableColumn<Emprestimo, LocalDate> colDataPrevDev = new TableColumn<>();
    @FXML
    private TableColumn<Emprestimo, LocalDate> colDataDev = new TableColumn<>();

    private DaoEmprestimo daoEmprestimo = new DaoEmprestimo();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }

    // Métodos de Eventos ---------------------------------------

    public void extrairRelatorio(ActionEvent e) {
        try {
            atualizarTabelaEmprestados();
            if(tabEmprestimo.getItems().isEmpty()){
                chamadaErro("Sem Pendências de Empréstimo.");
            }
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    @FXML
    public void voltarTela(ActionEvent e) {
        try {
            new GuiPrincipal().iniciar();
            GuiRelatorio.fecharTela();
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    @FXML
    public void limparTela(ActionEvent e) {
        tabEmprestimo.getItems().clear();
    }

    // Métodos auxiliares ---------------------------------------

    private void chamadaErro(String erro) {
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle("Alerta");
        dialogoInfo.setContentText(erro);
        dialogoInfo.showAndWait();
    }

    private void atualizarTabelaEmprestados() throws Exception{
        tabEmprestimo.getItems().clear();

        colId.setCellValueFactory(new PropertyValueFactory<Emprestimo, Long>("id"));
        colLeitorProfessor.setCellValueFactory(new PropertyValueFactory<Emprestimo, Professor>("leitorProfessor"));
        colLeitorAluno.setCellValueFactory(new PropertyValueFactory<Emprestimo, Aluno>("leitorAluno"));
        colExemplar.setCellValueFactory(new PropertyValueFactory<Emprestimo, Exemplar>("exemplar"));
        colDataEmp.setCellValueFactory(new PropertyValueFactory<Emprestimo, LocalDate>("dataEmprestimo"));
        colDataPrevDev.setCellValueFactory(new PropertyValueFactory<Emprestimo, LocalDate>("dataPrevistaDevolucao"));
        colDataDev.setCellValueFactory(new PropertyValueFactory<Emprestimo, LocalDate>("dataDevolucao"));

        List<Emprestimo> emprestimos = daoEmprestimo.listarTodosEmprestados();
        List<Emprestimo> emprestimosPendentes = new ArrayList<>();

        for(Emprestimo emprestimo : emprestimos){
            if(emprestimo.getDataDevolucao() == null & LocalDate.now().isAfter(emprestimo.getDataPrevistaDevolucao())){
                emprestimosPendentes.add(emprestimo);
            }
        }
        ObservableList<Emprestimo> dados = FXCollections.observableArrayList(emprestimosPendentes);
        tabEmprestimo.setItems(dados);
    }
}
