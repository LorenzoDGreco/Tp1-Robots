package canlor.tp1robots.modelo.entidades;

import java.util.ArrayList;

public class Entidad {
    private final int[] posicion;
    int tipoEntidad; //0 jugador 1 robot1 2 robot2 3 explosion
    private boolean activo;

    public Entidad(int x, int y, int tipoEntidad) {
        posicion = new int[]{x, y};
        this.tipoEntidad = tipoEntidad;
        activo = true;
    }

    public int getX() {
        return posicion[0];
    }

    public void setX(int posicion) {
        this.posicion[0] = posicion;
    }

    public int getY() {
        return posicion[1];
    }

    public void setY(int posicion) {
        this.posicion[1] = posicion;
    }

    public int getTipoEntidad() {
        return tipoEntidad;
    }

    public void moverse(int x, int y, ArrayList<Entidad> enemigos) {
        int dx = Integer.compare(x, getX());
        int dy = Integer.compare(y, getY());

        setX(getX() + dx);
        setY(getY() + dy);
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public boolean huboColision(int x, int y) {
        return (getX() == x && getY() == y);
    }
}

