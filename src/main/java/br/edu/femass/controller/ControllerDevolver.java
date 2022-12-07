package br.edu.femass.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoEmprestimo;
import br.edu.femass.dao.DaoExemplar;
import br.edu.femass.gui.GuiDevolver;
import br.edu.femass.gui.GuiPrincipal;
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

public class ControllerDevolver implements Initializable {

    @FXML
    private Button btnProcessar;
    @FXML
    private Button btnVoltarTela;
    //--------------------------------------------------------------------------------
    @FXML
    private TableView<Emprestimo> tabEmprestimo = new TableView<Emprestimo>();
    @FXML
    private TableColumn<Emprestimo, Long> colIdEmp = new TableColumn<>();
    @FXML
    private TableColumn<Emprestimo, Professor> colLeitorProfessorEmp = new TableColumn<>();
    @FXML
    private TableColumn<Emprestimo, Aluno> colLeitorAlunoEmp = new TableColumn<>();
    @FXML
    private TableColumn<Emprestimo, Exemplar> colExemplarEmp = new TableColumn<>();
    @FXML
    private TableColumn<Emprestimo, LocalDate> colDataEmpEmp = new TableColumn<>();
    @FXML
    private TableColumn<Emprestimo, LocalDate> colDataPrevDevEmp = new TableColumn<>();
    @FXML
    private TableColumn<Emprestimo, LocalDate> colDataDevEmp = new TableColumn<>();
    //--------------------------------------------------------------------------------
    @FXML
    private TableView<Emprestimo> tabDevolucao = new TableView<Emprestimo>();
    @FXML
    private TableColumn<Emprestimo, Long> colIdDev = new TableColumn<>();
    @FXML
    private TableColumn<Emprestimo, Professor> colLeitorProfessorDev = new TableColumn<>();
    @FXML
    private TableColumn<Emprestimo, Aluno> colLeitorAlunoDev = new TableColumn<>();
    @FXML
    private TableColumn<Emprestimo, Exemplar> colExemplarDev = new TableColumn<>();
    @FXML
    private TableColumn<Emprestimo, LocalDate> colDataEmpDev = new TableColumn<>();
    @FXML
    private TableColumn<Emprestimo, LocalDate> colPrevDevDev = new TableColumn<>();
    @FXML
    private TableColumn<Emprestimo, LocalDate> colDataDevDev = new TableColumn<>();

    private DaoEmprestimo daoEmprestimo = new DaoEmprestimo();
    private DaoExemplar daoExemplar = new DaoExemplar();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try{
            atualizarTabelaDevolvidos();
            atualizarTabelaEmprestados();
        }
        catch (Exception ex){
            chamadaErro(ex.getMessage());
        }
    }

    // Métodos de Eventos ---------------------------------------
    
    public void processarDevolucao(ActionEvent e) {    
        try {
            Emprestimo emprestimo = tabEmprestimo.getSelectionModel().getSelectedItem();
            emprestimo.devolverEmprestimo();

            daoEmprestimo.modificar(emprestimo);
            daoExemplar.modificar(emprestimo.getExemplar());

            atualizarTabelaDevolvidos();
            atualizarTabelaEmprestados();
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    @FXML
    public void voltarTela(ActionEvent e) {
        try {
            new GuiPrincipal().iniciar();
            GuiDevolver.fecharTela();
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    // Métodos auxiliares ---------------------------------------

    private void chamadaErro(String erro) {
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle("Alerta");
        dialogoInfo.setContentText(erro);
        dialogoInfo.showAndWait();
    }

    private void atualizarTabelaDevolvidos() throws Exception{
        tabDevolucao.getItems().clear();

        colIdDev.setCellValueFactory(new PropertyValueFactory<Emprestimo, Long>("id"));
        colLeitorProfessorDev.setCellValueFactory(new PropertyValueFactory<Emprestimo, Professor>("leitorProfessor"));
        colLeitorAlunoDev.setCellValueFactory(new PropertyValueFactory<Emprestimo, Aluno>("leitorAluno"));
        colExemplarDev.setCellValueFactory(new PropertyValueFactory<Emprestimo, Exemplar>("exemplar"));
        colDataEmpDev.setCellValueFactory(new PropertyValueFactory<Emprestimo, LocalDate>("dataEmprestimo"));
        colPrevDevDev.setCellValueFactory(new PropertyValueFactory<Emprestimo, LocalDate>("dataPrevistaDevolucao"));
        colDataDevDev.setCellValueFactory(new PropertyValueFactory<Emprestimo, LocalDate>("dataDevolucao"));

        List<Emprestimo> emprestimos = daoEmprestimo.listarTodosDevolvidos();

        ObservableList<Emprestimo> dados = FXCollections.observableArrayList(emprestimos);
        tabDevolucao.setItems(dados);
    }

    private void atualizarTabelaEmprestados() throws Exception{
        tabEmprestimo.getItems().clear();

        colIdEmp.setCellValueFactory(new PropertyValueFactory<Emprestimo, Long>("id"));
        colLeitorProfessorEmp.setCellValueFactory(new PropertyValueFactory<Emprestimo, Professor>("leitorProfessor"));
        colLeitorAlunoEmp.setCellValueFactory(new PropertyValueFactory<Emprestimo, Aluno>("leitorAluno"));
        colExemplarEmp.setCellValueFactory(new PropertyValueFactory<Emprestimo, Exemplar>("exemplar"));
        colDataEmpEmp.setCellValueFactory(new PropertyValueFactory<Emprestimo, LocalDate>("dataEmprestimo"));
        colDataPrevDevEmp.setCellValueFactory(new PropertyValueFactory<Emprestimo, LocalDate>("dataPrevistaDevolucao"));
        colDataDevEmp.setCellValueFactory(new PropertyValueFactory<Emprestimo, LocalDate>("dataDevolucao"));

        List<Emprestimo> emprestimos = daoEmprestimo.listarTodosEmprestados();

        ObservableList<Emprestimo> dados = FXCollections.observableArrayList(emprestimos);
        tabEmprestimo.setItems(dados);
    }
}
