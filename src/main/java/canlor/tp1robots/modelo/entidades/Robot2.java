package canlor.tp1robots.modelo.entidades;

import java.util.ArrayList;

public class Robot2 extends Entidad {

    public Robot2(int x, int y) {
        super(x, y, 2);
    }

    @Override
    public void moverse(int x, int y, ArrayList<Entidad> enemigos) {
        darUnPaso(x, y);

        if (!huboColision(getX(), getY(), enemigos)) {
            darUnPaso(x, y);
        }
    }

    private void darUnPaso(int x, int y) {
        int dx = Integer.compare(x, getX());
        int dy = Integer.compare(y, getY());
        
        setX(getX() + dx);
        setY(getY() + dy);
    }

    private boolean huboColision(int x, int y, ArrayList<Entidad> enemigos) {
        for (Entidad entidad : enemigos) {
            if (entidad != this && entidad.getX() == x && entidad.getY() == y) {
                return true;
            }
        }
        return false;
    }
}