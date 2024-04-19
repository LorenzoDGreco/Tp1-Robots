package canlor.tp1robots.modelo.juego;

import canlor.tp1robots.modelo.entidades.Entidad;
import canlor.tp1robots.modelo.entidades.Jugador;

import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Juego {
    private Estado estado;
    private Movimiento movimiento;
    private Tps tps;

    private final Jugador jugador;
    private ArrayList<Entidad> enemigos;

    public Juego(int filas, int columnas) {
        jugador = new Jugador(filas/2, columnas/2);
        enemigos = new ArrayList<>();

        tps = new Tps();
        estado = new Estado(enemigos, jugador, tps, filas, columnas);
        movimiento = new Movimiento(enemigos, jugador, estado);
    }

    public void iniciar() {
        estado.iniciar();
    }

    public void reiniciar() {
        estado.reiniciar();
    }

    public void redimensionar(int filas, int columnas) {
        estado.redimensionar(filas, columnas);
    }

    public void mover(int x, int y) {
        int[] coords = new int[]{x,y};
        estado.comprobarEstadoPartida(() -> {
            if (tps.isTpSeguroActivado()) {
                movimiento.jugadorTP(coords);
                tps.realizarTpSeguro(jugador);
                return;
            }
            movimiento.moverTodo(coords);
        });
    }

    public void tpAleatorio(){
        estado.comprobarEstadoPartida(() -> {
            tps.setTpSeguro(false);
            int[] coords = tps.tpAleatorio(estado.getDimension());
            movimiento.jugadorTP(coords);
        });
    }

    public void tpSeguro() {
        tps.tpSeguro(jugador.getTpSeguros());
    }

    public void cambiarImagen() {
        jugador.cambiarImagen();
        for(Entidad entidad : enemigos) { entidad.cambiarImagen();}
    }

    public ArrayList<Entidad> getEnemigos() {
        return enemigos;
    }

    public int getTpSeguros() {
        return jugador.getTpSeguros();
    }

    public int[] getDimension() {
        return estado.getDimension();
    }

    public int[] getTamanioTotal() {
        int width = 16 + (estado.getDimension()[1] * 16);
        int height = 20 + 165 + (estado.getDimension()[0] * 16);
        return new int[]{width, height};
    }

    public int getNivel() {
        return estado.getNivel();
    }

    public int getJugadorX() {
        return jugador.getX();
    }

    public int getJugadorY() {
        return jugador.getY();
    }

    public BufferedImage getImagenJugador() {
        return jugador.getImagen();
    }

}
