package canlor.tp1robots.module.entidades;

public class Jugador extends Entidad {
    private int[] posicion;
    private int tpSeguros;
    private boolean vivo;

    public Jugador(int x, int y) {
        super(x, y, 0);
        this.tpSeguros = 1;
        this.vivo = true;
    }

    public int getTpSeguros() {
        return tpSeguros;
    }

    public void setTpSeguros(int tpSeguros) {
        this.tpSeguros = tpSeguros;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }
}
