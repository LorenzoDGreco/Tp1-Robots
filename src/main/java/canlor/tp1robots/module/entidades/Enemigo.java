package canlor.tp1robots.module.entidades;

public abstract class Enemigo extends Entidad {

    public Enemigo(int x, int y, int tipoEntidad) {
        super(x, y, tipoEntidad);
    }

    abstract void MoverseAlJugador();

    public boolean Colision(int x, int y) {
        return (getX() == x && getY() == y);
    }
}