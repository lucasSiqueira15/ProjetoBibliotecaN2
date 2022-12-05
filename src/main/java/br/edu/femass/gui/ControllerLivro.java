package br.edu.femass.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.edu.femass.dao.DaoAutor;
import br.edu.femass.dao.DaoLivro;
import br.edu.femass.model.Autor;
import br.edu.femass.model.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class ControllerLivro implements Initializable {

    @FXML
    private Button btnInserirLivro;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnVoltarTela;
    @FXML
    private Button btnCadastrarExemplares;
    @FXML
    private Button btnProcessar;
    @FXML
    private Button btnVoltarOpcoes;
    @FXML
    private Button btnInserirAutor;
    @FXML
    private Button btnCadastrarAutor;
    @FXML
    private Button btnExcluirAutor;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtAno;
    @FXML
    private TextField txtTitulo;
    @FXML
    private ComboBox<Autor> cboAutores;
    @FXML
    private TableView<Livro> tabLivros = new TableView<Livro>();
    @FXML
    private TableColumn<Livro, Long> colIdLivro = new TableColumn<>();
    @FXML
    private TableColumn<Livro, String> colAno = new TableColumn<>();
    @FXML
    private TableColumn<Livro, String> colTitulo = new TableColumn<>();
    @FXML
    private TableColumn<Livro, List<Autor>> colAutor = new TableColumn<>();
    @FXML
    private TableView<Autor> tabAutores = new TableView<Autor>();
    @FXML
    private TableColumn<Autor, Long> colIdAutor = new TableColumn<>();
    @FXML
    private TableColumn<Autor, String> colNome = new TableColumn<>();
    @FXML
    private TableColumn<Autor, String> colSobrenome = new TableColumn<>();
    @FXML
    private TableColumn<Autor, String> colNacionalidade = new TableColumn<>();

    DaoLivro daoLivro = new DaoLivro();
    DaoAutor daoAutor = new DaoAutor();
    private String opcaoProcessamento = "";

    //Métodos para os eventos da tela--------------------------

    @FXML
    public void inserirLivro(ActionEvent e) {
        opcaoProcessamento = "inserir";
        limparDados();
        visualizacaoTela(true);
    }

    @FXML
    public void alterarLivro(ActionEvent e) {
        if (tabLivros.getSelectionModel().isEmpty()) {
            chamadaErro("Por favor, escolha um item para alterar!");
        } else {
            opcaoProcessamento = "alterar";
            visualizacaoTela(true);
        }
    }

    @FXML
    public void excluirLivro(ActionEvent e) {
        try {
            daoLivro.deletar(tabLivros.getSelectionModel().getSelectedItem());
            atualizarTabelaLivro();
            limparDados();
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    @FXML
    public void voltarTela(ActionEvent e) {
        try {
            new GuiPrincipal().iniciar();
            GuiLivro.fecharTela();
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    @FXML
    public void abrirTelaExemplares(ActionEvent e) {
        try {
            //Abrir Tela Exemplar
            GuiLivro.fecharTela();
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
    public void abrirTelaAutor(ActionEvent e) {
        try {
            new GuiAutor().iniciar(ControllerLivro.toNomeTela());
            GuiLivro.fecharTela();
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    @FXML
    public void excluirAutor(ActionEvent e) {
        tabAutores.getItems().remove(tabAutores.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void inserirAutor(ActionEvent e) {
        Autor autor = cboAutores.getSelectionModel().getSelectedItem();
        declararColunasTabelaAutor();
        tabAutores.getItems().add(autor);
    }

    @FXML
    public void voltarOpcoes(ActionEvent e) {
        limparDados();
        visualizacaoTela(false);
    }

    @FXML
    public void processarLivro(ActionEvent e) {
        try {
            if (opcaoProcessamento.equals("inserir")) {
                cadastrarLivro();
            } else {
                modificarLivro();
                visualizacaoTela(false);
            }
            atualizarTabelaLivro();
            limparDados();
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }

    //Inicialização da Tela-------------------------------------

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            atualizarTabelaLivro();
            atualizarComboAutores();
            visualizacaoTela(false);
        } catch (Exception ex) {
            chamadaErro(ex.getMessage());
        }
    }


    //Métodos Auxiliares-----------------------------------------

    public static String toNomeTela() {
        return "Livro";
    }

    private void atualizarTabelaAutor() throws Exception {
        tabAutores.getItems().clear();

        declararColunasTabelaAutor();

        Livro livro = tabLivros.getSelectionModel().getSelectedItem();

        ObservableList<Autor> dados = FXCollections.observableArrayList(livro.getAutores());
        tabAutores.setItems(dados);
    }

    private void declararColunasTabelaAutor(){
        colNome.setCellValueFactory(
                new PropertyValueFactory<Autor, String>("nome"));

        colSobrenome.setCellValueFactory(
                new PropertyValueFactory<Autor, String>("sobrenome"));

        colNacionalidade.setCellValueFactory(
                new PropertyValueFactory<Autor, String>("nacionalidade"));

        colIdAutor.setCellValueFactory(
                new PropertyValueFactory<Autor, Long>("id"));
    }

    private void atualizarTabelaLivro() throws Exception {
        tabLivros.getItems().clear();

        colAno.setCellValueFactory(
                new PropertyValueFactory<Livro, String>("ano"));

        colTitulo.setCellValueFactory(
                new PropertyValueFactory<Livro, String>("titulo"));

        colAutor.setCellValueFactory(
                new PropertyValueFactory<Livro, List<Autor>>("autores"));

        colIdLivro.setCellValueFactory(
                new PropertyValueFactory<Livro, Long>("id"));

        List<Livro> livros = daoLivro.listarTodos();

        ObservableList<Livro> dados = FXCollections.observableArrayList(livros);
        tabLivros.setItems(dados);
    }

    private void exibirDados() throws Exception {
        Livro livro = tabLivros.getSelectionModel().getSelectedItem();

        if (livro == null)
            return;

        txtId.setText(livro.getId().toString());
        txtAno.setText(livro.getAno());
        txtTitulo.setText(livro.getTitulo());

        atualizarTabelaAutor();
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
        btnInserirAutor.setDisable(!entrada);
        btnCadastrarAutor.setDisable(!entrada);
        btnExcluirAutor.setDisable(!entrada);
        txtAno.setDisable(!entrada);
        txtTitulo.setDisable(!entrada);
        cboAutores.setDisable(!entrada);
        tabAutores.setDisable(!entrada);

        btnVoltarTela.setDisable(entrada);
        btnInserirLivro.setDisable(entrada);
        btnAlterar.setDisable(entrada);
        btnExcluir.setDisable(entrada);
        btnCadastrarExemplares.setDisable(entrada);
        tabLivros.setDisable(entrada);
    }

    private void limparDados() {
        txtId.setText("");
        txtAno.setText("");
        txtTitulo.setText("");
        tabAutores.getItems().clear();
        cboAutores.getSelectionModel().clearSelection();
        // cboAutores.getSelectionModel().select(-1);
    }

    private void cadastrarLivro() {
        List<Autor> autores = new ArrayList<Autor>();
        for (int i = 0; i < tabAutores.getItems().size(); i++) {
            autores.add(tabAutores.getItems().get(i));
        }

        if(autores.isEmpty()){
            chamadaErro("POR FAVOR, INSERIR UM AUTOR PARA PODER CADASTRAR SEU LIVRO.");
        }
        else {
            daoLivro.adicionar(new Livro(txtTitulo.getText(), txtAno.getText(), autores));
        }
    }

    private void modificarLivro() {
        Livro livro = tabLivros.getSelectionModel().getSelectedItem();

        livro.setAno(txtAno.getText());
        livro.setTitulo(txtTitulo.getText());

        List<Autor> autores = new ArrayList<Autor>();
        FXCollections.copy(tabAutores.getItems(), autores);

        livro.setAutores(autores);

        daoLivro.modificar(livro);
    }

    private void atualizarComboAutores() throws Exception {
        cboAutores.getItems().clear();
        List<Autor> autores = new DaoAutor().listarTodos();
        ObservableList<Autor> dados = FXCollections.observableArrayList(autores);
        cboAutores.setItems(dados);
        // cboAutores.getSelectionModel().select(-1);
    }

}
