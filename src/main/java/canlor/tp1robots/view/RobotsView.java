package canlor.tp1robots.view;

import canlor.tp1robots.module.juego.Juego;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class RobotsView {
    private VBox root;
    private Menu menu;
    private StackPane union;
    private Tablero tablero;
    private Botones botones;
    private Scene scene;

    private Juego modelo;

    public RobotsView(Stage stage, Juego modelo, int filas, int columnas) throws IOException {
        stage.setTitle("Robots");

        root = new VBox();

        menu = new Menu();
        root.getChildren().add(menu.getMenuBar());

        tablero = new Tablero(filas,columnas,modelo);
        root.getChildren().add(tablero.getTablero());

        botones = new Botones(modelo);
        root.getChildren().add(botones.getBotones());

        scene = new Scene(root, 720, 625);

        stage.setScene(scene);
        stage.show();
    }

    public void redimensionar() {
        tablero.actualizarDimension(modelo.getDimension());
        //scene Cambiar el tamaño correspondiente para que entre
    }

    public void actualizar() {
        //tablero.reiniciar(); //Casi seguro de que va pero quiero ver como se comporta la grilla sin reiniciar por actualizacion | hacerlo priv y que este en posiciones
        tablero.actualizarPosiciones();
        botones.actualizarBoton();
    }

    public void crearEventos(ArrayList<EventHandler<ActionEvent>> ListEvents) {
        //menu.crearEvento();
    }

    public String[] getRedimensiones() {
        return menu.getRedimensiones();
    }
}