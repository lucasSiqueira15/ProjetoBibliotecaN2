package br.edu.femass.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuiLivro{

    private static Stage tela;

    public GuiLivro(){
        
    }
    
    public void iniciar() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/livro.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        scene.getRoot().setStyle("-fx-font-family: 'serif'");

        Stage stage = new Stage();
        tela = stage;
        stage.setTitle("Menu Livro");
        stage.setScene(scene);
        stage.show();
    }

    public static void fecharTela(){
        tela.close();
    }
}

