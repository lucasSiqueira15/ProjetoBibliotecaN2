package br.edu.femass.gui;

import java.util.Enumeration;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GuiAutor{

    public GuiAutor(){
        
    }
    
    public void iniciar(String nomeTelaAnterior) throws Exception {
        final String nomeTelaPassada = nomeTelaAnterior;

        ResourceBundle nomeTela = new ResourceBundle(){
            @Override
            public String getBaseBundleName() {
                return nomeTelaPassada;
            }

            @Override
            protected Object handleGetObject(String key) {
                throw new UnsupportedOperationException("Utilizar o método getBaseBundleName().");
            }

            @Override
            public Enumeration<String> getKeys() {
                throw new UnsupportedOperationException("Utilizar o método getBaseBundleName().");
            }

        };

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/autor.fxml"), nomeTela);
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        scene.getRoot().setStyle("-fx-font-family: 'serif'");

        Stage stage = new Stage();
        stage.setTitle("Menu Autor");
        stage.setScene(scene);
        stage.show();
    }

}

