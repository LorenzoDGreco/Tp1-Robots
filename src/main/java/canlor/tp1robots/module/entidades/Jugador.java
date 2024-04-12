package canlor.tp1robots.module.entidades;

public class Jugador extends Entidad {
    private int[] posicion;
    private int tpSeguros;

    public Jugador(int x, int y) {
        super(x, y, 0);
        this.tpSeguros = 1;
    }

    public int getTpSeguros() {
        return tpSeguros;
    }

    public void setTpSeguros(int tpSeguros) {
        this.tpSeguros = tpSeguros;
    }

}
