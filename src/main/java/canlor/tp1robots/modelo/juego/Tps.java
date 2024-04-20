package canlor.tp1robots.modelo.juego;

import canlor.tp1robots.modelo.entidades.Jugador;

import java.util.Random;

/**
 * Representa los Teletransportes del juego Robots. Es una clase que comprende el total de Juego
 * Contiene un atributo que indica si el tpSeguro esta activado o no y metodos para realizar teletransportes seguros o aleatorios
 */
public class Tps {
    private boolean tpSeguroActivado;

    /**
     * Constructor de la instancia de Tps. Inicializa el tpSeguroActivado en falso
     */
    public Tps() {
        tpSeguroActivado = false;
    }

    /**
     * Devuelve una posicion aleatoria
     * @param dimension dimension del tablero
     * @return un arreglo de dos posiciones con las coordenadas de la posicion aleatoria
     */
    public int[] tpAleatorio(int[] dimension) {
        return posicionAleatoria(dimension);
    }

    /**
     * Activa el tpSeguro si la cantidad de teletransportes disponibles es mayor a 0
     * @param cantTps cantidad de teletransportes disponibles
     */
    public void tpSeguro(int cantTps) {
        if (cantTps > 0) {
            tpSeguroActivado = true;
        }
    }

    /**
     * Devuelve si el tpSeguro esta activado
     * @return true si el tpSeguro esta activado, false en caso contrario
     */
    public boolean isTpSeguroActivado() {
        return tpSeguroActivado;
    }

    /**
     * Activa o desactiva el tpSeguro
     * @param es true para activar el tpSeguro, false para desactivarlo
     */
    public void setTpSeguro(boolean es) {
        tpSeguroActivado = es;
    }

    /**
     * Devuelve una posicion aleatoria
     * @param dimension dimension del tablero
     * @return un arreglo de dos posiciones con las coordenadas de la posicion aleatoria
     */
    public int[] posicionAleatoria(int[] dimension) {
        Random rand = new Random();
        return new int[]{rand.nextInt(dimension[0]), rand.nextInt(dimension[1])};
    }

    /**
     * Activa el tpSeguro y disminuye en 1 la cantidad de teletransportes seguros disponibles
     * @param jugador Entidad que representa al jugador
     */
    public void realizarTpSeguro(Jugador jugador) {
        tpSeguroActivado = false;
        jugador.setTpSeguros(jugador.getTpSeguros() - 1);
    }
}
