package canlor.tp1robots.modelo.juego;

import canlor.tp1robots.modelo.entidades.*;

import java.util.ArrayList;

public class Estado {
    private final int[] cantRobotsInicial = new int[]{4,2};
    private final int[] dimension;
    private int nivel;

    private ArrayList<Entidad> enemigos;
    private Jugador jugador;
    private Tps tps;

    public Estado(ArrayList<Entidad> enemigos, Jugador jugador, Tps tps, int filas, int columnas) {
        this.enemigos = enemigos;
        this.jugador = jugador;
        dimension = new int[]{filas, columnas};
        nivel = 1;
        this.tps = tps;
    }

    public void reiniciar() {
        tps.setTpSeguro(false);
        nivel = 1;
        enemigos.clear();
        jugador.setTpSeguros(1);
        iniciar();
    }

    public void iniciar() {
        enemigos.clear();
        tps.setTpSeguro(false);
        jugador.setX(dimension[0] / 2);
        jugador.setY(dimension[1] / 2);
        jugador.setActivo(true);

        for (int i = 0; i < cantRobotsInicial[0] * nivel; i++) {
            int[] coords = tps.posicionAleatoria(dimension);
            enemigos.add(new Robot1(coords[0], coords[1]));
        }

        for (int i = 0; i < cantRobotsInicial[1] * nivel; i++) {
            int[] coords = tps.posicionAleatoria(dimension);
            enemigos.add(new Robot2(coords[0], coords[1]));
        }
    }

    public boolean terminoPartida() {
        if (!jugador.isActivo()) {
            return true;
        }

        boolean gano = true;
        for (Entidad enemigo : enemigos) {
            if (!(enemigo instanceof Explosion)) {
                gano = false;
                break;
            }
        }
        if (gano) {
            nivel += 1;
            jugador.setTpSeguros(jugador.getTpSeguros() + 1);
        }
        return gano;
    }

    public void comprobarEstadoPartida(Runnable fun) {
        if (!terminoPartida()) {
            fun.run();
        } else {
            if (jugador.isActivo()){
                iniciar();
            } else {
                reiniciar();
            }
        }
    }

    public void redimensionar(int x, int y) {
        dimension[0] = x;
        dimension[1] = y;
    }

    public int[] getDimension() {
        return dimension;
    }

    public int getNivel() {
        return nivel;
    }
}
