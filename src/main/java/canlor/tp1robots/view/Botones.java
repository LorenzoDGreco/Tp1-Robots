package canlor.tp1robots.view;

import canlor.tp1robots.controlador.Controlador;
import canlor.tp1robots.modelo.juego.Juego;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 * Representa los botones de la interfaz
 * Contiene metodos para incializar los botones, actualizarlos y manejar eventos asciados
 */
public class Botones {
    private final Controlador controlador;
    private final Juego modelo;

    private GridPane gb;
    private Button tpSeguro;

    /**
     * Constructor de la clase Botones
     *
     * @param modelo      modelo del Juego que se esta jugando
     * @param controlador
     */
    public Botones(Juego modelo, Controlador controlador) {
        gb = new GridPane();
        this.modelo = modelo;
        this.controlador = controlador;
        
        inicializarBotones();
    }

    /**
     * Inicializa los botones de la interfaz
     */
    private void inicializarBotones() {
        gb = new GridPane();

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(33.33);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(33.33);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(33.33);
        gb.getColumnConstraints().addAll(column1, column2, column3);

        Button esperar = new Button("Wait for Robots");
        Button tpAleatorio = new Button("Teleport Randomly");
        tpSeguro = new Button("Teleport Safely\n(Remaining: " + modelo.getTpSeguros() + ")");


        tpAleatorio.setMinHeight(120);
        esperar.setMinHeight(120);
        tpSeguro.setMinHeight(120);

        tpAleatorio.setMaxWidth(Double.MAX_VALUE);
        esperar.setMaxWidth(Double.MAX_VALUE);
        tpSeguro.setMaxWidth(Double.MAX_VALUE);


        tpAleatorio.setOnAction(_ -> controlador.tpAleatorio());
        esperar.setOnAction(_ -> controlador.esperar());
        tpSeguro.setOnAction(_ -> controlador.tpSeguro());


        gb.add(tpAleatorio, 0, 0);
        gb.add(tpSeguro, 1, 0);
        gb.add(esperar, 2, 0);
    }

    /**
     * Devuelve el GridPane con los botones
     * @return GridPane con los botones
     */
    public GridPane getBotones() {
        return gb;
    }

    /**
     * Actualiza el texto del boton de Teleport Seguro
     */
    public void actualizarBoton() {
        tpSeguro.setText("Teleport Safely\n(Remaining: " + modelo.getTpSeguros() + ")");
    }
}

