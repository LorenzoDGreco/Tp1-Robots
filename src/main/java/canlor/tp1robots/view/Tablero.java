package canlor.tp1robots.view;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tablero {
    private GridPane gp;

    public Tablero(GridPane gp) {
        this.gp = gp;
    }

    public void inicializarTablero(int filasTotales, int columnasTotales) {
        Color color;
        for (int i = 0; i < filasTotales; i++) {
            for (int j = 0; j < columnasTotales; j++) {
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
}
