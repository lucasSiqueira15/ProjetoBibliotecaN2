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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML
    private Button btnProcessar;
    @FXML
    private Button btnVoltarTela;
    @FXML
    private Button btnVoltarOpcoes;
    @FXML
    private Button btnInserir;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;

    private String telaAnterior;
    DaoAutor daoAutor = new DaoAutor();
    private String opcaoProcessamento = "";

    @FXML
    public void processarAutor(ActionEvent e) {
        try {
            if (opcaoProcessamento.equals("inserir")) {
                cadastrarAutor();
            } else {
                modificarAutor();
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
    public void inserirAutor(ActionEvent e) {
        opcaoProcessamento = "inserir";
        limparTxt();
        visualizacaoTela(true);
    }

    @FXML
    public void alterarAutor(ActionEvent e) {
        if (tabAutores.getSelectionModel().isEmpty()) {
            chamadaErro("Por favor, escolha um item para alterar!");
        } else {
            opcaoProcessamento = "alterar";
            visualizacaoTela(true);
        }

    }

    @FXML
    public void excluirAutor(ActionEvent e) {
        try {
            daoAutor.deletar(tabAutores.getSelectionModel().getSelectedItem());
            atualizarTabela();
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    @FXML
    public void voltarTela(ActionEvent e) {
        try {
            if (telaAnterior.equals("Principal")) {
                new GuiPrincipal().iniciar();
                GuiAutor.fecharTela();
            } else {
                if (telaAnterior.equals("Livro")) {
                    // GuiLivro telaLivro = new GuiLivro();
                } else {
                    chamadaErro("Erro ao carregar a tela anterior.");
                }
            }
        } catch (Exception ex) {
            chamadaErro("Erro ao carregar a tela anterior.");
        }
    }

    private void atualizarTabela() throws Exception {
        tabAutores.getItems().clear();
        
        colNome.setCellValueFactory(
                new PropertyValueFactory<Autor, String>("nome"));

        colSobrenome.setCellValueFactory(
                new PropertyValueFactory<Autor, String>("sobrenome"));

        colNacionalidade.setCellValueFactory(
                new PropertyValueFactory<Autor, String>("nacionalidade"));

        colId.setCellValueFactory(
                new PropertyValueFactory<Autor, Long>("id"));

        List<Autor> autores = daoAutor.listarTodos();

        ObservableList<Autor> dados = FXCollections.observableArrayList(autores);
        tabAutores.setItems(dados);
    }

    private void exibirDados() {
        Autor autor = tabAutores.getSelectionModel().getSelectedItem();

        if (autor == null)
            return;

        txtId.setText(autor.getId().toString());
        txtNome.setText(autor.getNome());
        txtSobrenome.setText(autor.getSobrenome());
        txtNacionalidade.setText(autor.getNacionalidade());
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
        btnInserir.setDisable(entrada);
        btnAlterar.setDisable(entrada);
        btnExcluir.setDisable(entrada);
        txtNome.setDisable(!entrada);
        txtSobrenome.setDisable(!entrada);
        txtNacionalidade.setDisable(!entrada);
        tabAutores.setDisable(entrada);
    }

    private void limparTxt() {
        txtId.setText("");
        txtNome.setText("");
        txtSobrenome.setText("");
        txtNacionalidade.setText("");
    }

    private void cadastrarAutor() {
        daoAutor.adicionar(new Autor(txtNome.getText(), txtSobrenome.getText(), txtNacionalidade.getText()));
    }

    private void modificarAutor() {
        Autor autor = tabAutores.getSelectionModel().getSelectedItem();

        autor.setNome(txtNome.getText());
        autor.setSobrenome(txtSobrenome.getText());
        autor.setNacionalidade(txtNacionalidade.getText());

        daoAutor.modificar(autor);
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