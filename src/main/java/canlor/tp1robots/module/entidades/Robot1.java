package canlor.tp1robots.module.entidades;

public class Robot1 extends Enemigo {
    public Robot1(int x, int y) {
        super(x, y, 1);
    }

    @Override
    void MoverseAlJugador() {

    }

    @Override
    public boolean Colision(int x, int y) {
        return super.Colision(x, y);
    }
}
