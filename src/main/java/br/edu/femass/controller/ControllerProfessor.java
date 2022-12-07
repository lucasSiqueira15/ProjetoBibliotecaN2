package br.edu.femass.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoProfessor;
import br.edu.femass.gui.GuiLeitor;
import br.edu.femass.gui.GuiPrincipal;
import br.edu.femass.gui.GuiProfessor;
import br.edu.femass.model.Professor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ControllerProfessor implements Initializable {

    @FXML
    private Button btnProcessar;
    @FXML
    private Button btnVoltarOpcoes;
    @FXML
    private Button btnInserir;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnVoltarTela;
    @FXML
    private Button btnPrincipal;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEndereco;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtDevolucao;
    @FXML
    private TextField txtDisciplina;
    @FXML
    private TableView<Professor> tabProfessores = new TableView<Professor>();
    @FXML
    private TableColumn<Professor, Long> colId = new TableColumn<>();
    @FXML
    private TableColumn<Professor, String> colNome = new TableColumn<>();
    @FXML
    private TableColumn<Professor, String> colEndereco = new TableColumn<>();
    @FXML
    private TableColumn<Professor, String> colTelefone = new TableColumn<>();
    @FXML
    private TableColumn<Professor, Long> colDevolucao = new TableColumn<>();
    @FXML
    private TableColumn<Professor, String> colDisciplina = new TableColumn<>();

    private String telaAnterior = "";
    private DaoProfessor daoProfessor = new DaoProfessor();
    private String opcaoProcessamento = "";

    @FXML
    public void processarProfessor(ActionEvent e) {
        try {
            if (opcaoProcessamento.equals("inserir")) {
                cadastrarProfessor();
            } else {
                modificarProfessor();
                visualizacaoTela(false);
            }
            atualizarTabela();
            limparTxt();
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    @FXML
    public void voltarOpcoes(ActionEvent e) {
        limparTxt();
        visualizacaoTela(false);
    }

    @FXML
    public void selecionarLinhaTeclado(KeyEvent e) {
        exibirDados();
    }

    @FXML
    public void selecionarLinhaMouse(MouseEvent e) {
        exibirDados();
    }

    @FXML
    public void inserirProfessor(ActionEvent e) {
        opcaoProcessamento = "inserir";
        limparTxt();
        visualizacaoTela(true);
    }

    @FXML
    public void alterarProfessor(ActionEvent e) {
        if (tabProfessores.getSelectionModel().isEmpty()) {
            chamadaErro("Por favor, escolha um item para alterar!");
        } else {
            opcaoProcessamento = "alterar";
            visualizacaoTela(true);
        }
    }

    @FXML
    public void excluirProfessor(ActionEvent e) {
        try {
            daoProfessor.deletar(tabProfessores.getSelectionModel().getSelectedItem());
            atualizarTabela();
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    @FXML
    public void voltarTela(ActionEvent e) {
        try {
            new GuiLeitor().iniciar(telaAnterior);
            GuiProfessor.fecharTela();
        } catch (Exception ex) {
            chamadaErro("Erro ao carregar a tela do menu principal.");
        }
    }

    @FXML
    public void voltarPrincipal(ActionEvent e) {
        try {
            new GuiPrincipal().iniciar();
            GuiProfessor.fecharTela();
        } catch (Exception ex) {
            chamadaErro("Erro ao carregar a tela do menu principal.");
        }
    }

    private void atualizarTabela() throws Exception {
        tabProfessores.getItems().clear();

        colId.setCellValueFactory(
                new PropertyValueFactory<Professor, Long>("id"));

        colNome.setCellValueFactory(
                new PropertyValueFactory<Professor, String>("nome"));

        colEndereco.setCellValueFactory(
                new PropertyValueFactory<Professor, String>("endereco"));

        colTelefone.setCellValueFactory(
                new PropertyValueFactory<Professor, String>("telefone"));

        colDevolucao.setCellValueFactory(
                new PropertyValueFactory<Professor, Long>("prazoMaximoDevolucao"));

        colDisciplina.setCellValueFactory(
                new PropertyValueFactory<Professor, String>("disciplina"));

        List<Professor> professores = daoProfessor.listarTodos();

        ObservableList<Professor> dados = FXCollections.observableArrayList(professores);
        tabProfessores.setItems(dados);
    }

    private void exibirDados() {
        Professor professor = tabProfessores.getSelectionModel().getSelectedItem();

        if (professor == null)
            return;

        txtId.setText(professor.getId().toString());
        txtNome.setText(professor.getNome());
        txtEndereco.setText(professor.getEndereco());
        txtTelefone.setText(professor.getTelefone());
        txtDevolucao.setText(professor.getPrazoMaximoDevolucao().toString());
        txtDisciplina.setText(professor.getDisciplina());
    }

    private void chamadaErro(String erro) {
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle("Alerta");
        dialogoInfo.setContentText(erro);
        dialogoInfo.showAndWait();
    }

    private void visualizacaoTela(Boolean entrada) {
        btnProcessar.setDisable(!entrada);
        btnVoltarOpcoes.setDisable(!entrada);
        btnVoltarTela.setDisable(entrada);
        btnPrincipal.setDisable(entrada);
        btnInserir.setDisable(entrada);
        btnAlterar.setDisable(entrada);
        btnExcluir.setDisable(entrada);
        txtNome.setDisable(!entrada);
        txtEndereco.setDisable(!entrada);
        txtTelefone.setDisable(!entrada);
        txtDisciplina.setDisable(!entrada);
        tabProfessores.setDisable(entrada);
    }

    private void limparTxt() {
        txtId.setText("");
        txtNome.setText("");
        txtEndereco.setText("");
        txtTelefone.setText("");
        txtDevolucao.setText("");
        txtDisciplina.setText("");
    }

    private void cadastrarProfessor() {
        daoProfessor.adicionar(
                new Professor(txtNome.getText(), txtEndereco.getText(), txtTelefone.getText(), txtDisciplina.getText()));
    }

    private void modificarProfessor() {
        Professor professor = tabProfessores.getSelectionModel().getSelectedItem();

        professor.setNome(txtNome.getText());
        professor.setEndereco(txtEndereco.getText());
        professor.setTelefone(txtTelefone.getText());
        professor.setDisciplina(txtDisciplina.getText());

        daoProfessor.modificar(professor);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            telaAnterior = arg1.getBaseBundleName();
            visualizacaoTela(false);
            atualizarTabela();
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

}
