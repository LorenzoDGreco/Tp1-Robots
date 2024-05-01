package canlor.tp1robots.modelo.entidades;

import java.util.ArrayList;

/**
 * Representa a un tipo de entidad Explosion en el juego.
 * Tiene posicion pero es fija, no se mueve
 */
public class Explosion extends Entidad {
    /**
     * Construye una explosion en las coordenadas x e y dadas
     * @param x coordenada x de la explosion
     * @param y coordenada y de la explosion
     */
    public Explosion(int x, int y) {
        super(x, y, new int[]{13}, 0);
    }

    /**
     * Override del metodo de la clase Entidad
     * La explosion no se mueve, entonces el metodo no hace nada
     * @param x no se usa
     * @param y no se usa
     * @param enemigos no se usa
     */
    @Override
    public void moverse(int x, int y, ArrayList<Entidad> enemigos) {
    }

}
