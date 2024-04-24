package canlor.tp1robots.view;

import canlor.tp1robots.controlador.Eventos;
import canlor.tp1robots.modelo.entidades.Entidad;
import canlor.tp1robots.modelo.juego.Juego;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Representa el tablero del juego Robots
 * Contiene metodos para inicializar el tablero, actualizarlo y crear eventos
 */
public class Tablero {
    private final int[] COLOR_CASILLA1 = new int[]{251, 205, 231};
    private final int[] COLOR_CASILLA2 = new int[]{255, 228, 243};
    private final int TILE = 16;

    private final Juego modelo;
    private final GridPane gp;
    private int[] dimension;

    /**
     * Constructor del tablero. Inicializa el tablero con las dimensiones dadas
     * @param x cantidad de filas del tablero
     * @param y cantidad de columnas del tablero
     * @param modelo modelo del juego que se esta jugando
     */
    public Tablero(int x, int y, Juego modelo) {
        gp = new GridPane();
        this.modelo = modelo;
        dimension = new int[]{x, y};

        inicializarTablero();
    }

    /**
     * Inicializa el tablero con las dimensiones dadas
     */
    private void inicializarTablero() {
        Color color;
        for (int i = 0; i < dimension[0]; i++) {  //Si se redimensiona al reves cambiar esto
            for (int j = 0; j < dimension[1]; j++) {
                if ((i + j) % 2 == 0) {
                    color = Color.rgb(COLOR_CASILLA1[0], COLOR_CASILLA1[1], COLOR_CASILLA1[2]);
                } else {
                    color = Color.rgb(COLOR_CASILLA2[0], COLOR_CASILLA2[1], COLOR_CASILLA2[2]);
                }
                Rectangle rect = new Rectangle(TILE, TILE, color);
                gp.add(rect, j, i);
            }
        }
    }

    /**
     * Actualiza las dimensiones del tablero
     */
    public void actualizarDimension(int[] nueva) {
        dimension = nueva;
        reiniciar();
    }

    /**
     * Actualiza visualmente las posiciones de los robots y el jugador en el tablero
     */
    public void actualizarPosiciones() {
        ArrayList<Entidad> enemigos = modelo.getEnemigos();

        for (Entidad entidad : enemigos) {

            Image imagenEntidad = convertToJavaFXImage(entidad.getImagen());
            ImageView imagenVistaEntidad = new ImageView(imagenEntidad);
            gp.add(imagenVistaEntidad, entidad.getY(), entidad.getX());
        }

        Image imagenJugador = convertToJavaFXImage(modelo.getImagenJugador());
        ImageView imagenVistaJugador = new ImageView(imagenJugador);

        gp.add(imagenVistaJugador, modelo.getJugadorY(), modelo.getJugadorX());
    }

    /**
     * Convierte una BufferedImage a una Image de JavaFX
     * @param bufferedImage imagen a convertir
     * @return Image de JavaFX
     */
    private Image convertToJavaFXImage(BufferedImage bufferedImage) {
        try {
            // Convertir BufferedImage a bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);
            byte[] bytes = baos.toByteArray();

            // Convertir bytes a Image de JavaFX
            return new Image(new ByteArrayInputStream(bytes));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Reinicia el tablero manteniendo las mismas dimensiones
     */
    public void reiniciar() {
        gp.getChildren().clear();
        inicializarTablero();
        actualizarPosiciones();
    }

    /**
     * Crea el evento de click en el tablero
     * @param eventos eventos que se van a crear
     */
    public void crearEvento(Eventos eventos) {
        gp.setOnMouseClicked(eventos.getMouseClick());
    }

    /**
     * Devuelve el GridPane del tablero
     * @return GridPane del tablero
     */
    public GridPane getTablero() {
        return gp;
    }
}
