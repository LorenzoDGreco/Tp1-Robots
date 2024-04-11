package canlor.tp1robots.module.entidades;

public class Robot2 extends Entidad {

    public Robot2(int x, int y) {
        super(x, y, 2);
    }

    @Override
    public void moverse(int x, int y) {
        moverse(x, y);
        moverse(x, y);
    }

}