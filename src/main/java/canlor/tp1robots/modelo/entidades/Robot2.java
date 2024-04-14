package canlor.tp1robots.modelo.entidades;

import java.util.ArrayList;

public class Robot2 extends Entidad {

    public Robot2(int x, int y) {
        super(x, y, 2);
    }

    @Override
    public void moverse(int x, int y, ArrayList<Entidad> enemigos) {
        int dx = Integer.compare(x, getX());
        int dy = Integer.compare(y, getY());

        // Primer movimiento
        int newX = getX() + dx;
        int newY = getY() + dy;

        setX(newX);
        setY(newY);

        // Verificar colisión en el primer movimiento
        if (!huboColision(newX, newY, enemigos)) {
            // Segundo movimiento si no hay colisión en el primer movimiento
            dx = Integer.compare(x, getX());
            dy = Integer.compare(y, getY());

            newX += dx;
            newY += dy;

            setX(newX);
            setY(newY);

        }
    }

    private boolean huboColision(int x, int y, ArrayList<Entidad> enemigos) {
        for (Entidad entidad : enemigos) {
            if (entidad != this && entidad.isActivo() && entidad.getX() == x && entidad.getY() == y) {
                return true;
            }
        }
        return false;
    }
}