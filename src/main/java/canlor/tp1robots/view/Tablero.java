package canlor.tp1robots.view;

import javafx.scene.control.MenuBar;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tablero {
    private GridPane gp;
    private int[] dimension;

    public Tablero(int x, int y) {
        gp = new GridPane();
        dimension = new int[]{x, y};

        inicializarTablero();
    }


    private void inicializarTablero() {
        gp = new GridPane();

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
    }

    public GridPane getTablero() {
        return gp;
    }
}
