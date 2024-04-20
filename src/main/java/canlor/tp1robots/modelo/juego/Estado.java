package canlor.tp1robots.modelo.juego;

import canlor.tp1robots.modelo.entidades.*;

import java.util.ArrayList;

/**
 * Representa el estado del juego Robots, es una clase que comprende el total de Juego
 * Contiene la cantidad de robots iniciales, la dimension del tablero, el nivel actual, los enemigos
 * Ademas de una referencia al Jugador y a Tps, inicializadas en Juego
 */
public class Estado {
    private final int[] cantRobotsInicial = new int[]{4,2};
    private final int[] dimension;
    private int nivel;

    private ArrayList<Entidad> enemigos;
    private Jugador jugador;
    private Tps tps;

    /**
     * Constructor de la instancia de Estado
     * @param enemigos ArrayList que contiene a los enemigos
     * @param jugador Entidad que representa al jugador
     * @param tps Instancia de Tps
     * @param filas cantidad de filas iniciales para la dimension del tablero
     * @param columnas cantidad de columnas iniciales para la dimension del tablero
     */
    public Estado(ArrayList<Entidad> enemigos, Jugador jugador, Tps tps, int filas, int columnas) {
        this.enemigos = enemigos;
        this.jugador = jugador;
        dimension = new int[]{filas, columnas};
        nivel = 1;
        this.tps = tps;
    }

    /**
     * Reinicia el juego desactivando el tpSeguro, reiniciando el nivel y a los enemigos, y luego iniciando el juego
     */
    public void reiniciar() {
        nivel = 1;
        jugador.setTpSeguros(1);
        iniciar();
    }

    /**
     * Inicia el juego reiniciando y posicionando a los enemigos, desactivando el tpSeguro y reviviendo al jugador y
     * ubicandolo en el centro del tablero
     */
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

    /**
     * Comprueba si la partida termino, si el jugador no esta activo (perdio) o si todos los enemigos son instancias de Explosion (gano)
     * @return true si la partida termino, false en caso contrario
     */
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

    /**
     * Chequea el estado actual del juego, si no termino la partida, ejecuta la funcion pasada por parametro
     * Si termino la partida, si el jugador esta activo, inicia el proximo nivel, si no lo esta, lo reinicia
     * @param fun funcion a ejecutar si no termino la partida
     */
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

    /**
     * Setea las nuevas dimensiones del tablero
     * @param x nueva cantidad de filas
     * @param y nueva cantidad de columnas
     */
    public void redimensionar(int x, int y) {
        dimension[0] = x;
        dimension[1] = y;
    }

    /**
     * Devuelve la cantidad de filas y columnas del tablero
     * @return int[] con la cantidad de filas y columnas del tablero
     */
    public int[] getDimension() {
        return dimension;
    }

    /**
     * Devuelve el nivel actual
     * @return nivel actual
     */
    public int getNivel() {
        return nivel;
    }
}
