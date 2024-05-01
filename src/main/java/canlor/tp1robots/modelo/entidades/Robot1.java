package canlor.tp1robots.modelo.entidades;

/**
 * Representa a un tipo de entidad Robot1.
 * Tiene posicion y una cantidad de puntos que da cuando muere.
 */
public class Robot1 extends Entidad {
    /**
     * Construye un Robot1 en las coordenadas x e y dadas.
     * @param x coordenadas x del robot1
     * @param y coordenadas y del robot1
     */
    public Robot1(int x, int y) {
        super(x, y, new int[]{5,6,7,8}, 10);
    }

}
