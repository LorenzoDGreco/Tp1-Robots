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

    private final int[] posicion;
    private boolean activo;

    /**
     * Contruye una entidad dada su posicion y su tipo
     * @param x la coordenada de la entidad
     * @param y la coordenada de la entidad
     */
    public Entidad(int x, int y) {
        hs = new HojaSprite();
        posicion = new int[]{x, y};
        activo = true;
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

    protected void cargarImagenes(int[] imagenes) {
        fotosEntidad = hs.getSprites(imagenes);
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
     * @param posicion
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
     * @param posicion
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
     * @param activo
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}

