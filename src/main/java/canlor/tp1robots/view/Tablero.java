package canlor.tp1robots.view;

import canlor.tp1robots.controlador.Eventos;
import canlor.tp1robots.modelo.entidades.Entidad;
import canlor.tp1robots.modelo.entidades.Explosion;
import canlor.tp1robots.modelo.entidades.Robot1;
import canlor.tp1robots.modelo.entidades.Robot2;
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
                    color = Color.rgb(106, 130, 158);
                } else {
                    color = Color.rgb(118, 142, 169);
                }
                Rectangle rect = new Rectangle(16, 16, color);
                gp.add(rect, j, i);
            }
        }
    }

    public void actualizarDimension(int[] nueva) {
        dimension = nueva;
        reiniciar();
    }

    public void actualizarPosiciones() {
        ArrayList<Entidad> enemigos = modelo.getEnemigos();

        gp.add(new Rectangle(10, 10, Color.YELLOW), modelo.getJugadorY(), modelo.getJugadorX());

        for (Entidad entidad : enemigos) {

            Image image = convertToJavaFXImage(entidad.getImagen());
            ImageView imageView = new ImageView(image);
            gp.add(imageView, entidad.getY(), entidad.getX());


            //provisional...
            /*
            if (entidad instanceof Explosion) {
                gp.add(new Rectangle(10, 10, Color.RED), entidad.getY(), entidad.getX());
            } else if (entidad instanceof Robot2) {
                gp.add(new Rectangle(10, 10, Color.BROWN), entidad.getY(), entidad.getX());
            } else if (entidad instanceof Robot1) {
                gp.add(new Rectangle(10, 10, Color.DARKGRAY), entidad.getY(), entidad.getX());
            }*/
        }
    }

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

    public void reiniciar() {
        gp.getChildren().clear();
        inicializarTablero();
        actualizarPosiciones();
    }

    public void crearEvento(Eventos eventos) {
        gp.setOnMouseClicked(eventos.getMouseClick());
    }

    public GridPane getTablero() {
        return gp;
    }
}
