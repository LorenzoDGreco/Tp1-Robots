package canlor.tp1robots.view;

import canlor.tp1robots.modelo.entidades.Entidad;
import canlor.tp1robots.modelo.juego.Juego;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Tablero {
    private final Juego modelo;
    private final GridPane gp;
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
        reiniciar();
    }

    public void actualizarPosiciones() {
        ArrayList<Entidad> enemigos =  modelo.getEnemigos();

        gp.add(new Rectangle(10,10, Color.YELLOW), modelo.getJugador().getY(), modelo.getJugador().getX());

        for (Entidad entidad : enemigos) {
            //provisional...
            if (entidad.getTipoEntidad() == 3) {
                gp.add(new Rectangle(10,10, Color.RED), entidad.getY(), entidad.getX());
            } else if (entidad.getTipoEntidad() == 2) {
                gp.add(new Rectangle(10,10, Color.BROWN), entidad.getY(), entidad.getX());
            } else if (entidad.getTipoEntidad() == 1) {
                gp.add(new Rectangle(10,10, Color.DARKGRAY), entidad.getY(), entidad.getX());
            }
        }
    }

    public void reiniciar() {
        gp.getChildren().clear();
        inicializarTablero();
        actualizarPosiciones();
    }

    public GridPane getTablero() {
        return gp;
    }
}
