package canlor.tp1robots.view;

import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.geometry.Pos;


public class Botones {

    private GridPane gb;
    private Button TpAleatorio;
    private Button TpSeguro;
    private Button Esperar;

    public Botones(GridPane botones) {
        gb = botones;
    }

    public void inicializarBotones() {
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(33.33);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(33.33);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(33.33);
        gb.getColumnConstraints().addAll(column1, column2, column3);

        TpAleatorio = new Button("Teletransportarse Aleatoriamente");
        TpSeguro = new Button("Teletransporte Seguro");
        Esperar = new Button("Esperar a los Robots");

        TpAleatorio.setMinHeight(120);
        TpSeguro.setMinHeight(120);
        Esperar.setMinHeight(120);

        TpAleatorio.setMaxWidth(Double.MAX_VALUE);
        TpSeguro.setMaxWidth(Double.MAX_VALUE);
        Esperar.setMaxWidth(Double.MAX_VALUE);

        gb.add(TpAleatorio, 0, 0);
        gb.add(TpSeguro, 1, 0);
        gb.add(Esperar, 2, 0);
    }
}

