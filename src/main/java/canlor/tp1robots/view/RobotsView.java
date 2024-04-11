package canlor.tp1robots.view;

import canlor.tp1robots.module.juego.Juego;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class RobotsView {

    public RobotsView(Stage stage, Juego modelo, int filas, int columnas) throws IOException {
        stage.setTitle("Robots");

        VBox root = new VBox();

        Menu menu = new Menu();
        root.getChildren().add(menu.getMenuBar());

        Tablero tablero = new Tablero(30,45);
        root.getChildren().add(tablero.getTablero());

        Botones botones = new Botones();
        root.getChildren().add(botones.getBotones());

        Scene scene = new Scene(root, 720, 625);

        stage.setScene(scene);
        stage.show();
    }
}