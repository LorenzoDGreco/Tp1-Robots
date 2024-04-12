package canlor.tp1robots.module.entidades;

public class Entidad {
    private int[] posicion;
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

    public void moverse(int x, int y) {
        if (getX() == x && getY() == y) {
            return;
        } else if (getX() == x) {
            if (getY() < y) {
                setY(getY() + 1);
            } else {
                setY(getY() - 1);
            }
        } else if (getY() == y) {
            if (getX() < x) {
                setX(getX() + 1);
            } else {
                setX(getX() - 1);
            }
        } else { // diagonales
            if (getY() > y && getX() < x) { // esta arriba a la izq
                setY(getY() + 1);
                setX(getX() - 1);
            } else if (getY() > y && getX() > x) { // abajo a la der
                setY(getY() + 1);
                setX(getX() + 1);
            } else if (getY() < y && getX() < x) {
                setY(getY() - 1);
                setX(getX() - 1);
            } else if (getY() < y && getX() > x) {
                setY(getY() - 1);
                setX(getX() + 1);
            }
        }
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

