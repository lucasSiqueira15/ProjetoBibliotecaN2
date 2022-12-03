package br.edu.femass.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class GuiPrincipal extends Application{

    private static Stage tela;

    public GuiPrincipal() {

    }

    public void iniciar(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        scene.getRoot().setStyle("-fx-font-family: 'serif'");

        tela = stage;
        stage.setTitle("Menu Principal");
        stage.setScene(scene);
        stage.show();
    }

    public void iniciar() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        scene.getRoot().setStyle("-fx-font-family: 'serif'");

        Stage stage = new Stage();
        tela = stage;
        stage.setTitle("Menu Principal");
        stage.setScene(scene);
        stage.show();
    }

    public static void fecharTela(){
        tela.close();
    }

    @Override
    public void start(Stage arg0) throws Exception {
        this.iniciar(arg0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
