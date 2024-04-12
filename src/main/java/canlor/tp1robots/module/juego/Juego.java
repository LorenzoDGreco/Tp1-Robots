package canlor.tp1robots.module.juego;

import canlor.tp1robots.module.entidades.*;

import java.util.ArrayList;
import java.util.Random;

public class Juego {
    final int[] cantRobotsInicial;
    private int[] dimension;
    private ArrayList<Entidad> enemigos;
    private Jugador jugador;
    private int nivel = 0;

    public Juego(int filas, int columnas) {
        cantRobotsInicial = new int[]{4,2};
        dimension = new int[]{filas, columnas}; // a checkear
        nivel = 1;
        enemigos = new ArrayList<>();
        jugador = new Jugador(filas/2, columnas/2);
    }

    public void iniciar() {
        jugador.setX(dimension[0]/2);
        jugador.setY(dimension[1]/2);

        for (int i = 0; i < cantRobotsInicial[0] * nivel; i++) {
            int[] coords = PosicionAleatoria();
            enemigos.add(new Robot1(coords[0], coords[1]));
        }

        for (int i = 0; i < cantRobotsInicial[1] * nivel; i++) {
            int[] coords = PosicionAleatoria();
            enemigos.add(new Robot2(coords[0], coords[1]));
        }
    }

    public boolean TerminoPartida() {
        if (jugador.isActivo()) {
            return false; // el jugador esta vivo: no termino la partida
        }
        //Ganar partida
        boolean gano = true;
        for (Entidad enemigo : enemigos) {
            if (enemigo.getTipoEntidad() == 1 || enemigo.getTipoEntidad() == 2) {
                gano = false; // todavia quedan robots, todavia no gano
            }
        }
        if (gano) {
            nivel +=1;
        }
        return gano;
    }

    public int[] PosicionAleatoria(){
        Random rand = new Random();

        int X = rand.nextInt(dimension[1]);
        int Y = rand.nextInt(dimension[0]);

        return new int[]{X, Y};
    }

    public void mover(int x, int y) {
        moverJugador(x, y);
        moverRobots();
    }

    private void moverJugador(int x, int y) {
        jugador.moverse(x, y);
    }

    private void moverRobots() {
        ArrayList<Entidad> arrayAux = new ArrayList<>();
        for (Entidad entidad : enemigos) {
            entidad.moverse(jugador.getX(), jugador.getY());
            if (entidad.getTipoEntidad() == 2) {
                arrayAux.add(entidad);
            }
        }
        // si es robot2 se mueve segun colision
        Colision();
        for (Entidad enemigos : arrayAux) {
            if (this.enemigos.contains(enemigos)) {
                enemigos.moverse(jugador.getX(), jugador.getY());
            }
        }
        Colision();
    }

    private void Colision() {
        for (Entidad enemigo1 : enemigos) {
            if (jugador.huboColision(enemigo1.getX(), enemigo1.getY())) {
                jugador.setActivo(false);
            }
            for (Entidad enemigo2 : enemigos) {
                if (enemigo1.huboColision(enemigo2.getX(), enemigo2.getY())) {
                    enemigos.add(new Explosion(enemigo1.getX(), enemigo1.getY()));
                    enemigos.remove(enemigo1);
                    enemigos.remove(enemigo2);
                }
            }
        }
    }



    public void TpAleatorio() {
        int[] coords = PosicionAleatoria();
        jugador.setY(coords[1]);
        jugador.setX(coords[0]);
        moverRobots();
    }

    private boolean HayTpSeguro() {
        return jugador.getTpSeguros() > 0;
    }

    public void TpSeguro(int x, int y) {
        if (HayTpSeguro()) {
            jugador.setX(x);
            jugador.setY(y);
            jugador.setTpSeguros(jugador.getTpSeguros()-1);
            moverRobots();
        }

    }

    public void redimensionar(int x, int y) {
        dimension[0] = x;
        dimension[1] = y;
    }

    public ArrayList<Entidad> getEnemigos() {
        return enemigos;
    }

    public int getTpSeguros() {
        return jugador.getTpSeguros();
    }

    public Jugador getJugador() {
        return jugador;
    }

    public int[] getDimension() {
        return dimension;
    }
}
