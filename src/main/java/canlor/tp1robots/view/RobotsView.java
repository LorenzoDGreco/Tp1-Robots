package canlor.tp1robots.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class RobotsView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Robots");

        VBox root = new VBox();

        GridPane tablero = new GridPane();

        Tablero tableroInstance = new Tablero(tablero);
        tableroInstance.inicializarTablero(30,45);

        GridPane botones = new GridPane();
        Botones botonesInstance = new Botones(botones);
        botonesInstance.inicializarBotones();

        root.getChildren().addAll(tablero, botones);

        Scene scene = new Scene(root, 720, 600);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}