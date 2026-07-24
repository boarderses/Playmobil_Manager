package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(
                getClass().getResource("/view/main.fxml")
        );

        Scene scene = new Scene(root);
        stage.setTitle("Playmobil Manager");
        
        stage.setScene(scene);
        scene.getStylesheets().add(
                getClass().getResource("/css/style.css").toExternalForm());
        
        //Tamaño inicial
        stage.setWidth(1450);
        stage.setHeight(900);
        //Tamaño mínimo
        stage.setMinWidth(1200);
        stage.setMinHeight(800);
        
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}