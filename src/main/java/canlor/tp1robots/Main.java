package canlor.tp1robots;

import canlor.tp1robots.controlador.Controlador;
import canlor.tp1robots.modelo.juego.Juego;
import canlor.tp1robots.view.RobotsView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private RobotsView vista;

    @Override
    public void start(Stage stage) throws Exception {
        int filas = 30;
        int columnas = 45;

        Juego modelo = new Juego(filas, columnas);
        vista = new RobotsView(stage, modelo, filas, columnas);
        Controlador controlador = new Controlador(modelo, vista);
        controlador.iniciar();
    }

    @Override
    public void stop() {
        // Detener el timer cuando la aplicación se cierra
        vista.detenerTimer();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
