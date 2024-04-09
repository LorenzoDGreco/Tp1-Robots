package canlor.tp1robots.module.entidades;

public class Explosion extends Enemigo {
    public Explosion(int x, int y) {
        super(x, y, 3);
    }

    @Override
    void MoverseAlJugador() {}

    @Override
    public boolean Colision(int x, int y) {
        return super.Colision(x, y);
    }
}
