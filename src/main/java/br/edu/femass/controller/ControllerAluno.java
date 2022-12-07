package br.edu.femass.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAluno;
import br.edu.femass.gui.GuiAluno;
import br.edu.femass.gui.GuiLeitor;
import br.edu.femass.gui.GuiPrincipal;
import br.edu.femass.model.Aluno;
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

public class ControllerAluno implements Initializable {

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
    private TextField txtMatricula;
    @FXML
    private TableView<Aluno> tabAlunos = new TableView<Aluno>();
    @FXML
    private TableColumn<Aluno, Long> colId = new TableColumn<>();
    @FXML
    private TableColumn<Aluno, String> colNome = new TableColumn<>();
    @FXML
    private TableColumn<Aluno, String> colEndereco = new TableColumn<>();
    @FXML
    private TableColumn<Aluno, String> colTelefone = new TableColumn<>();
    @FXML
    private TableColumn<Aluno, Long> colDevolucao = new TableColumn<>();
    @FXML
    private TableColumn<Aluno, String> colMatricula = new TableColumn<>();

    private String telaAnterior = "";
    private DaoAluno daoAluno = new DaoAluno();
    private String opcaoProcessamento = "";

    @FXML
    public void processarAluno(ActionEvent e) {
        try {
            if (opcaoProcessamento.equals("inserir")) {
                cadastrarAluno();
            } else {
                modificarAluno();
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
    public void inserirAluno(ActionEvent e) {
        opcaoProcessamento = "inserir";
        limparTxt();
        visualizacaoTela(true);
    }

    @FXML
    public void alterarAluno(ActionEvent e) {
        if (tabAlunos.getSelectionModel().isEmpty()) {
            chamadaErro("Por favor, escolha um item para alterar!");
        } else {
            opcaoProcessamento = "alterar";
            visualizacaoTela(true);
        }
    }

    @FXML
    public void excluirAluno(ActionEvent e) {
        try {
            daoAluno.deletar(tabAlunos.getSelectionModel().getSelectedItem());
            atualizarTabela();
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    @FXML
    public void voltarTela(ActionEvent e) {
        try {
            new GuiLeitor().iniciar(telaAnterior);
            GuiAluno.fecharTela();
        } catch (Exception ex) {
            chamadaErro("Erro ao carregar a tela do menu principal.");
        }
    }

    @FXML
    public void voltarPrincipal(ActionEvent e) {
        try {
            new GuiPrincipal().iniciar();
            GuiAluno.fecharTela();
        } catch (Exception ex) {
            chamadaErro("Erro ao carregar a tela do menu principal.");
        }
    }

    private void atualizarTabela() throws Exception {
        tabAlunos.getItems().clear();

        colId.setCellValueFactory(
                new PropertyValueFactory<Aluno, Long>("id"));

        colNome.setCellValueFactory(
                new PropertyValueFactory<Aluno, String>("nome"));

        colEndereco.setCellValueFactory(
                new PropertyValueFactory<Aluno, String>("endereco"));

        colTelefone.setCellValueFactory(
                new PropertyValueFactory<Aluno, String>("telefone"));

        colDevolucao.setCellValueFactory(
                new PropertyValueFactory<Aluno, Long>("prazoMaximoDevolucao"));

        colMatricula.setCellValueFactory(
                new PropertyValueFactory<Aluno, String>("matricula"));

        List<Aluno> alunos = daoAluno.listarTodos();

        ObservableList<Aluno> dados = FXCollections.observableArrayList(alunos);
        tabAlunos.setItems(dados);
    }

    private void exibirDados() {
        Aluno aluno = tabAlunos.getSelectionModel().getSelectedItem();

        if (aluno == null)
            return;

        txtId.setText(aluno.getId().toString());
        txtNome.setText(aluno.getNome());
        txtEndereco.setText(aluno.getEndereco());
        txtTelefone.setText(aluno.getTelefone());
        txtDevolucao.setText(aluno.getPrazoMaximoDevolucao().toString());
        txtMatricula.setText(aluno.getMatricula());
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
        txtMatricula.setDisable(!entrada);
        tabAlunos.setDisable(entrada);
    }

    private void limparTxt() {
        txtId.setText("");
        txtNome.setText("");
        txtEndereco.setText("");
        txtTelefone.setText("");
        txtDevolucao.setText("");
        txtMatricula.setText("");
    }

    private void cadastrarAluno() {
        daoAluno.adicionar(
                new Aluno(txtNome.getText(), txtEndereco.getText(), txtTelefone.getText(), txtMatricula.getText()));
    }

    private void modificarAluno() {
        Aluno aluno = tabAlunos.getSelectionModel().getSelectedItem();

        aluno.setNome(txtNome.getText());
        aluno.setEndereco(txtEndereco.getText());
        aluno.setTelefone(txtTelefone.getText());
        aluno.setMatricula(txtMatricula.getText());

        daoAluno.modificar(aluno);
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
