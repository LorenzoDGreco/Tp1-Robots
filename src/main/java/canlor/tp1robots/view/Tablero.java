package canlor.tp1robots.view;

import canlor.tp1robots.module.entidades.Entidad;
import canlor.tp1robots.module.juego.Juego;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Tablero {
    private final Juego modelo;
    private GridPane gp;
    private int[] dimension;

    public Tablero(int x, int y, Juego modelo) {
        gp = new GridPane();
        this.modelo = modelo;
        dimension = new int[]{x, y};

        inicializarTablero();
    }


    private void inicializarTablero() {
        Color color;
        for (int i = 0; i < dimension[0]; i++) {  //Si se redimensiona al reves cambiar esto
            for (int j = 0; j < dimension[1]; j++) {
                if ((i + j) % 2 == 0) {
                    color = Color.rgb(106,130,158);
                } else {
                    color = Color.rgb(118,142,169);
                }
                Rectangle rect = new Rectangle(16,16, color);
                gp.add(rect, j, i);
            }
        }
    }

    public void actualizarDimension(int[] nueva) {
        dimension = nueva;
        inicializarTablero();
        actualizarPosiciones();
    }

    public void actualizarPosiciones() {
        ArrayList<Entidad> enemigos =  modelo.getEnemigos();

        gp.add(new Rectangle(10,10, Color.YELLOW), modelo.getJugador().getX(), modelo.getJugador().getY());

        for (Entidad entidad : enemigos) {
            gp.add(new Rectangle(10,10, Color.BLACK), entidad.getX(), entidad.getY());
        }
    }

    public void reiniciar() {
        gp.getChildren().clear();
        inicializarTablero();
    }

    public GridPane getTablero() {
        return gp;
    }
}
