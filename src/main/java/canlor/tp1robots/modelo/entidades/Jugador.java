package canlor.tp1robots.modelo.entidades;

/**
 * Representa a un tipo de entidad Jugador
 * Tiene una posicion y una cantidad de teletransportes seguros
 */
public class Jugador extends Entidad {
    private int tpSeguros;
    private int puntos;

    /**
     * Contruye a un jugador en las coordenadas x e y dadas.
     * Empieza con una cantidad fija
     * @param x coordenada x del jugador
     * @param y coordenada y del jugador
     */
    public Jugador(int x, int y) {
        super(x, y, new int[]{0,1,2,3,4}, 0);
        this.tpSeguros = 1;
        this.puntos = 0;
    }

    /**
     * Devuelve la cantidad de teletransportes seguros que tiene el jugador
     * @return int cantidad de teletransportes seguros
     */
    public int getTpSeguros() {
        return tpSeguros;
    }

    /**
     * Setea la cantidad de teletransportes seguros que tiene el jugador
     * @param tpSeguros cantidad de teletransportes seguros
     */
    public void setTpSeguros(int tpSeguros) {
        this.tpSeguros = tpSeguros;
    }

    public int getPuntos() {
        return puntos;
    }

    public void addPuntos(int puntos) {
        this.puntos += puntos;
    }

    public void resetPuntos() {
        puntos = 0;
    }

}
