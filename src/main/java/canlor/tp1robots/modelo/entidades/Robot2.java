package canlor.tp1robots.modelo.entidades;

import java.util.ArrayList;

/**
 * Representa un tipo de entidad Robot2.
 * Avanza de a dos pasos, tiene posicion.
 */
public class Robot2 extends Entidad {
    /**
     * Construye un robot en las coordenadas x e y dadas.
     * @param x coordenada x del robot
     * @param y coordenada y del robot
     */
    public Robot2(int x, int y) {
        super(x, y, new int[]{9,10,11,12}, 30);
    }

    /**
     * Mueve al robot hacia las coordenadas x e y dadas. Antes de dar el segundo paso chequea si hubo colision.
     * @param x coordenada x hacia la que se mueve
     * @param y coordenada y hacia la que se mueve
     * @param enemigos lista de enemigos en el juego
     */
    @Override
    public void moverse(int x, int y, ArrayList<Entidad> enemigos) {
        super.moverse(x, y, enemigos);

        if (!huboColision(getX(), getY(), enemigos)) {
            super.moverse(x, y, enemigos);
        }
    }

    /**
     * Chequea si hubo colision entre el robot y los demas enemigos
     * @param x coordenada x del robot
     * @param y coordenaaa y del robot
     * @param enemigos lista de enemigos en el juego
     * @return boolean true si hubo colision, false si no
     */
    private boolean huboColision(int x, int y, ArrayList<Entidad> enemigos) {
        for (Entidad entidad : enemigos) {
            if (entidad != this && entidad.getX() == x && entidad.getY() == y) {
                return true;
            }
        }
        return false;
    }
}