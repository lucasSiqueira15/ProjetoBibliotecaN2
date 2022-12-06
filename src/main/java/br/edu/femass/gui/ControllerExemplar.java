package br.edu.femass.gui;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoExemplar;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.model.Exemplar;
import br.edu.femass.model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ControllerExemplar implements Initializable {

    @FXML
    private Button btnInserir;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnVoltarTela;
    @FXML
    private Button btnProcessar;
    @FXML
    private Button btnVoltarOpcoes;
    @FXML
    private ComboBox<Livro> cboLivros;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtDisponivel;
    @FXML
    private DatePicker txtAquisicao;
    @FXML
    private TableView<Exemplar> tabExemplares = new TableView<Exemplar>();
    @FXML
    private TableColumn<Exemplar, Long> colId = new TableColumn<>();
    @FXML
    private TableColumn<Exemplar, Boolean> colDisponivel = new TableColumn<>();
    @FXML
    private TableColumn<Exemplar, LocalDate> colAquisicao = new TableColumn<>();
    @FXML
    private TableColumn<Exemplar, Livro> colLivro = new TableColumn<>();

    DaoLivro daoLivro = new DaoLivro();
    DaoExemplar daoExemplar = new DaoExemplar();
    private String opcaoProcessamento = "";

    // Métodos para os eventos da tela--------------------------

    @FXML
    public void inserirExemplar(ActionEvent e) {
        opcaoProcessamento = "inserir";
        limparDados();
        visualizacaoTelaPadrao(true);
    }

    @FXML
    public void alterarExemplar(ActionEvent e) {
        if (tabExemplares.getSelectionModel().isEmpty()) {
            chamadaErro("Por favor, escolha um item para alterar!");
        } else {
            opcaoProcessamento = "alterar";
            visualizacaoTelaAlterar(true);
        }
    }

    @FXML
    public void excluirExemplar(ActionEvent e) {
        try {
            daoExemplar.deletar(tabExemplares.getSelectionModel().getSelectedItem());
            atualizarTabelaExemplares();
            limparDados();
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    @FXML
    public void voltarTela(ActionEvent e) {
        try {
            new GuiLivro().iniciar();
            GuiExemplar.fecharTela();
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    @FXML
    public void selecionarLinhaTeclado(KeyEvent e) {
        try {
            exibirDados();
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    @FXML
    public void selecionarLinhaMouse(MouseEvent e) {
        try {
            exibirDados();
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    @FXML
    public void voltarOpcoes(ActionEvent e) {
        limparDados();
        visualizacaoTelaPadrao(false);
    }

    @FXML
    public void processarExemplar(ActionEvent e) {
        try {
            if (opcaoProcessamento.equals("inserir")) {
                if (cadastrarExemplar()) {
                    atualizarTabelaExemplares();
                }
            } 
            else {
                modificarExemplar();
                visualizacaoTelaPadrao(false);
                atualizarTabelaExemplares();
                limparDados();
            }
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    // Inicialização da Tela-------------------------------------

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            atualizarTabelaExemplares();
            atualizarComboLivros();
            visualizacaoTelaPadrao(false);
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    // Métodos Auxiliares-----------------------------------------

    private void atualizarComboLivros() throws Exception {
        cboLivros.getItems().clear();
        List<Livro> livros = new DaoLivro().listarTodos();
        ObservableList<Livro> dados = FXCollections.observableArrayList(livros);
        cboLivros.setItems(dados);
    }

    private void atualizarTabelaExemplares() throws Exception {
        tabExemplares.getItems().clear();

        colId.setCellValueFactory(
                new PropertyValueFactory<Exemplar, Long>("id"));

        colDisponivel.setCellValueFactory(
                new PropertyValueFactory<Exemplar, Boolean>("disponivel"));

        colAquisicao.setCellValueFactory(
                new PropertyValueFactory<Exemplar, LocalDate>("dataAquisicao"));

        colLivro.setCellValueFactory(
                new PropertyValueFactory<Exemplar, Livro>("livro"));

        List<Exemplar> exemplares = daoExemplar.listarTodos();

        ObservableList<Exemplar> dados = FXCollections.observableArrayList(exemplares);
        tabExemplares.setItems(dados);
    }

    private void chamadaErro(String erro) {
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle("Alerta");
        dialogoInfo.setContentText(erro);
        dialogoInfo.showAndWait();
    }

    private void exibirDados() throws Exception {
        Exemplar exemplar = tabExemplares.getSelectionModel().getSelectedItem();

        if (exemplar == null)
            return;

        txtId.setText(exemplar.getId().toString());
        txtAquisicao.setValue(exemplar.getDataAquisicao());
        if (exemplar.getDisponivel() == true) {
            txtDisponivel.setText("Disponível");
        } else {
            txtDisponivel.setText("Indisponível");
        }
        cboLivros.getSelectionModel().select(exemplar.getLivro());
    }

    private void visualizacaoTelaPadrao(Boolean entrada) {
        btnProcessar.setDisable(!entrada);
        btnVoltarOpcoes.setDisable(!entrada);
        txtAquisicao.setDisable(!entrada);
        cboLivros.setDisable(!entrada);

        btnVoltarTela.setDisable(entrada);
        btnInserir.setDisable(entrada);
        btnAlterar.setDisable(entrada);
        btnExcluir.setDisable(entrada);
        tabExemplares.setDisable(entrada);
    }

    private void visualizacaoTelaAlterar(Boolean entrada) {
        btnProcessar.setDisable(!entrada);
        btnVoltarOpcoes.setDisable(!entrada);
        txtAquisicao.setDisable(!entrada);
        cboLivros.setDisable(entrada);

        btnVoltarTela.setDisable(entrada);
        btnInserir.setDisable(entrada);
        btnAlterar.setDisable(entrada);
        btnExcluir.setDisable(entrada);
        tabExemplares.setDisable(entrada);
    }

    private void limparDados() {
        txtId.setText("");
        txtAquisicao.setValue(LocalDate.now());
        txtDisponivel.setText("");
        cboLivros.getSelectionModel().clearSelection();
    }

    private Boolean cadastrarExemplar(){
        if(cboLivros.getSelectionModel().getSelectedItem() == null){
            chamadaErro("POR FAVOR, INSERIR UM LIVRO PARA PODER CADASTRAR SEU EXEMPLAR.");
            return false;
        }
        else{
            daoExemplar.adicionar(
                new Exemplar(
                    txtAquisicao.getValue(), 
                    cboLivros.getSelectionModel().getSelectedItem()
                )
            );
            return true;
        }
    }

    private void modificarExemplar() {
        Exemplar exemplar = tabExemplares.getSelectionModel().getSelectedItem();

        exemplar.setDataAquisicao(txtAquisicao.getValue());

        daoExemplar.modificar(exemplar);
    }

}
