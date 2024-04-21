package canlor.tp1robots;

import canlor.tp1robots.controlador.Controlador;
import canlor.tp1robots.modelo.juego.Juego;
import canlor.tp1robots.view.RobotsView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    private final int FILAS = 30;
    private final int COLUMNAS = 45;
    private RobotsView vista;


    /**
     * Inicializa la aplicacion Robots
     * Instancia el modelo, la vista y controlador, y luego inicia el juego
     * @param stage stage principal del juego donde se va a setear la scene
     */
    @Override
    public void start(Stage stage) throws Exception {
        Juego modelo = new Juego(FILAS, COLUMNAS);
        vista = new RobotsView(stage, modelo, FILAS, COLUMNAS);
        Controlador controlador = new Controlador(modelo, vista);
        controlador.iniciar();
    }

    /**
     * Cierra la aplicacion
     * Detiene el timer cuando la aplicacion se cierra
     */
    @Override
    public void stop() {
        // Detener el timer cuando la aplicación se cierra
        vista.detenerTimer();
    }

    /**
     * Entry point de la aplicación Robots
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
