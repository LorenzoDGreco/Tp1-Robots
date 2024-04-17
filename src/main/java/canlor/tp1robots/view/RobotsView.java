package canlor.tp1robots.view;

import canlor.tp1robots.controlador.Eventos;
import canlor.tp1robots.modelo.juego.Juego;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;

public class RobotsView {
    private final VBox root;
    private final MenuOpciones menuOpciones;
    private final Tablero tablero;
    private final Botones botones;
    private final Scene scene;
    private final Stage stage;

    private final Juego modelo;
    private final Timer timer;

    public RobotsView(Stage stage, Juego modelo, int filas, int columnas) throws IOException {
        this.stage = stage;
        this.modelo = modelo;
        timer = new Timer();
        stage.setTitle("Robots");

        root = new VBox();

        menuOpciones = new MenuOpciones(modelo);
        root.getChildren().add(menuOpciones.getMenuBar());

        tablero = new Tablero(filas, columnas, modelo);
        root.getChildren().add(tablero.getTablero());

        botones = new Botones(modelo);
        root.getChildren().add(botones.getBotones());

        scene = new Scene(root, 720, 625);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public void redimensionar() {
        tablero.actualizarDimension(modelo.getDimension());

        int[] tamanio = modelo.getTamanioTotal();
        stage.setWidth(tamanio[0]);
        stage.setHeight(tamanio[1]);

        menuOpciones.cerrarVentana();
    }

    public void actualizar() {
        tablero.reiniciar();
        botones.actualizarBoton();
        menuOpciones.setNivelLabel();
    }

    public void crearEventos(Eventos eventos) {
        timer.scheduleAtFixedRate(eventos.getTimer(),0,200);
        scene.setOnKeyReleased(eventos.getTeclado());
        menuOpciones.crearEvento(eventos);
        botones.crearEvento(eventos);
        tablero.crearEvento(eventos);
    }

    public void setErrorLabel(String error) {
        menuOpciones.setErrorLabel(error);
    }

    public String[] getRedimensiones() {
        return menuOpciones.getRedimensiones();
    }

    public void detenerTimer() {
        timer.cancel();
    }
}

