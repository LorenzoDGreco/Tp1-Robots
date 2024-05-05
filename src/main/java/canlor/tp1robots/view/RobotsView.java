package canlor.tp1robots.view;

import canlor.tp1robots.controlador.Controlador;
import canlor.tp1robots.modelo.juego.Juego;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Timer;

/**
 * Representa la vista principal del juego Robots
 * Contiene metodos para crear la vista, redimensionarla, actualizarla y crear eventos
 */
public class RobotsView {
    private final int filas;
    private final int columnas;

    private VBox root;
    private MenuOpciones menuOpciones;
    private Tablero tablero;
    private Botones botones;
    private Scene scene;
    private final Stage stage;

    private final Juego modelo;
    private Controlador controlador;
    private Timer timer;

    /**
     * Constructor de la vista principal del juego Robots
     * @param stage stage donde el juego se mostrara
     * @param modelo modelo del juego que se esta jugando
     * @param filas cantidad filas del tablero
     * @param columnas cantidad de columnas del tablero
     */
    public RobotsView(Stage stage, Juego modelo, int filas, int columnas) {
        this.stage = stage;
        this.modelo = modelo;
        this.filas = filas;
        this.columnas = columnas;
    }

    public void iniciar() {
        stage.setTitle("Robots");

        root = new VBox();

        menuOpciones = new MenuOpciones(modelo, controlador);
        root.getChildren().add(menuOpciones.getMenuBar());

        tablero = new Tablero(filas, columnas, modelo, controlador);
        root.getChildren().add(tablero.getTablero());

        botones = new Botones(modelo, controlador);
        root.getChildren().add(botones.getBotones());

        scene = new Scene(root, 720, 625);
        scene.setOnKeyReleased(controlador::teclado);

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        timer = new Timer();
        timer.scheduleAtFixedRate(controlador.timer(),0,200);
    }

    public void getControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    /**
     * Redimensiona la ventana y el tablero del juego
     */
    public void redimensionar() {
        tablero.actualizarDimension(modelo.getDimension());

        int[] tamanio = modelo.getTamanioTotal();
        stage.setWidth(tamanio[0]);
        stage.setHeight(tamanio[1]);

        menuOpciones.cerrarVentana();
    }

    /**
     * Actualiza la vista del juego
     */
    public void actualizar() {
        tablero.reiniciar();
        botones.actualizarBoton();
        menuOpciones.setNivelLabel();
        menuOpciones.updatePuntos();
    }

    /**
     * Actualiza las animaciones del juego
     */
    public void actualizarAnimaciones() {
        tablero.reiniciar();
    }

    /**
     * Setea el mensaje de error en el menu de opciones
     * @param error mensaje de error
     */
    public void setErrorLabel(String error) {
        menuOpciones.setErrorLabel(error);
    }

    /**
     * Devuelve las dimensiones ingresadas
     * @return String[] dimensiones ingresadas
     */
    public String[] getRedimensiones() {
        return menuOpciones.getRedimensiones();
    }

    /**
     * Detiene el timer
     */
    public void detenerTimer() {
        timer.cancel();
    }
}

