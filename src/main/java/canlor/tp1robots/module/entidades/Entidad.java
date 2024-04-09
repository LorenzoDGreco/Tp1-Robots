package canlor.tp1robots.module.entidades;

public class Entidad {
    private int[] posicion;
    private
    int tipoEntidad; //0 jugador 1 robot1 2 robot2 3 explosion

    public Entidad(int x, int y, int tipoEntidad) {
        posicion = new int[]{x, y};
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
}
