package canlor.tp1robots.modelo.juego;

import canlor.tp1robots.modelo.entidades.Jugador;

import java.util.Random;

public class Tps {
    private boolean tpSeguroActivado;

    public Tps() {
        tpSeguroActivado = false;
    }

    public int[] tpAleatorio(int[] dimension) {
        return posicionAleatoria(dimension);
    }

    public void tpSeguro(int cantTps) {
        if (cantTps > 0) {
            tpSeguroActivado = true;
        }
    }

    public boolean isTpSeguroActivado() {
        return tpSeguroActivado;
    }

    public void setTpSeguro(boolean es) {
        tpSeguroActivado = es;
    }

    public int[] posicionAleatoria(int[] dimension) {
        Random rand = new Random();
        return new int[]{rand.nextInt(dimension[0]), rand.nextInt(dimension[1])};
    }

    public void realizarTpSeguro(Jugador jugador) {
        tpSeguroActivado = false;
        jugador.setTpSeguros(jugador.getTpSeguros() - 1);
    }
}
