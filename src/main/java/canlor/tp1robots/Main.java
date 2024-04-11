package canlor.tp1robots;

import canlor.tp1robots.controlador.Controlador;
import canlor.tp1robots.module.juego.Juego;
import canlor.tp1robots.view.RobotsView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        int filas = 30;
        int columnas = 45;

        Juego modelo = new Juego(filas,columnas);
        RobotsView vista = new RobotsView(stage, modelo, filas, columnas);
        Controlador controlador = new Controlador(modelo, vista);
        controlador.iniciar();
    }

    public static void main(String[] args) {
        launch();
    }
}
