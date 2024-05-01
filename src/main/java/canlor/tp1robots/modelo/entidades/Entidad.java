package canlor.tp1robots.modelo.entidades;

import canlor.tp1robots.modelo.graficos.HojaSprite;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Representa a un entidad en el juego: jugador, robot1, robot2, explosion
 * Cada entidad tiene una posicion, un estado activo o inactivo y un tipo de entidad: 0:jugador, 1:robot1, 2:robot2, 3:explosion
 */
public class Entidad {
    private HojaSprite hs;
    private BufferedImage[] fotosEntidad;
    private int indiceImagen;

    private final int[] posicion;
    private boolean activo;
    private int puntosQueDa;

    /**
     * Contruye una entidad dada su posicion y su tipo
     * @param x la coordenada de la entidad
     * @param y la coordenada de la entidad
     * @param posImagenes un array de las posiciones de las imagenes a procesar
     */
    public Entidad(int x, int y, int[] posImagenes, int puntos) {
        hs = new HojaSprite();
        posicion = new int[]{x, y};
        activo = true;
        indiceImagen = 0;
        puntosQueDa = puntos;
        cargarImagenes(posImagenes);
    }

    /**
     * Mueve a la entidad hacia las coordenadas x e y dadas
     * @param x coordenada x a la que se mueve
     * @param y coordenada y a la que se mueve
     * @param enemigos lista de enemigos en el juego
     */
    public void moverse(int x, int y, ArrayList<Entidad> enemigos) {
        int dx = Integer.compare(x, getX());
        int dy = Integer.compare(y, getY());

        setX(getX() + dx);
        setY(getY() + dy);
    }

    /**
     * Chequea si hubo una colision en las coordenadas x e y
     * @param x coordenada x donde se chequea por colision
     * @param y coordenada y donde se chequea por colision
     * @return true si hubo una colision en las coordenadas dadas
     */
    public boolean huboColision(int x, int y) {
        return (getX() == x && getY() == y);
    }

    /**
     * Dada las posiciones de las imagenes a procesar, carga las imagenes en el array de imagenes de la entidad
     * @param posImagenes un array imagenes a procesar
     */
    private void cargarImagenes(int[] posImagenes) {
        fotosEntidad = hs.getSprites(posImagenes);
    }

    /**
     * Cambia el indice que indica la imagen actual de la entidad.
     */
    public void cambiarImagen() {
        if (!isActivo()){
            indiceImagen = 4;
            return;
        }
        int fotosLength = fotosEntidad.length - 1;
        if (fotosLength > 3) { // si se esta cambiando la imagen del jugador que tiene 5 en vez de 4 como  los otros
            fotosLength--;
        }
        indiceImagen++; // va a la siguiente en el array asociado a la entidad
        if (indiceImagen > fotosLength) { indiceImagen = 0; }
    }

    /**
     * Devuelve la imagen actual de la entidad
     * @return la imagen de la entidad
     */
    public BufferedImage getImagen() {
        return fotosEntidad[indiceImagen];
    }

    /**
     * Devuelve la coordenada x de la entidad
     * @return la coordenada x de la entidad
     */
    public int getX() {
        return posicion[0];
    }

    /**
     * Establece la coordenada x de la entidad
     * @param posicion la coordenada x de la entidad
     */
    public void setX(int posicion) {
        this.posicion[0] = posicion;
    }

    /**
     * Devuelve la coordenada y de la entidad
     * @return la coordenada y de la entidad
     */
    public int getY() {
        return posicion[1];
    }

    /**
     * Establece coordenada y de la entidad
     * @param posicion la coordenada y de la entidad
     */
    public void setY(int posicion) {
        this.posicion[1] = posicion;
    }

    /**
     * Devuelve el estado de actividad de la entidad
     * @return activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * Establece estado de actividad de la entidad: se activa o se desactiva
     * @param activo el estado de actividad de la entidad
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getPuntosQueDa() {
        return puntosQueDa;
    }
}

