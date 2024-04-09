package canlor.tp1robots.module.entidades;

public class Jugador extends Entidad {
    private int tpSeguros;
    boolean vivo;

    public Jugador(int x, int y) {
        super(x, y, 0);
        this.tpSeguros = 1;
        vivo = true;
    }

    public int getTpSeguros() {
        return tpSeguros;
    }

    public void setTpSeguros(int tpSeguros) {
        this.tpSeguros = tpSeguros;
    }
}
