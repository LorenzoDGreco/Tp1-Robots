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

    public Juego(int filasTotales, int columnasTotales) {
        cantRobotsInicial = new int[]{4,2};
        dimension = new int[]{filasTotales, columnasTotales}; // a checkear
        nivel = 1;
        enemigos = new ArrayList<>();
        jugador = new Jugador(filasTotales/2, columnasTotales/2);
    }

    public int[] getDimension() {
        return dimension;
    }

    public void Iniciar() {
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
        //Perder Partida
        if (jugador.isVivo()) {
            return false;
        }
        //Ganar partida
        boolean aux = true;
        for (Entidad enemigo : enemigos) {
            if (enemigo.getTipoEntidad() == 1 || enemigo.getTipoEntidad() == 2) {
                aux = false;
            }
        }
        if (aux) {
            nivel +=1;
        }
        return aux;
    }

    public int[] PosicionAleatoria(){
        Random rand = new Random();

        int X = rand.nextInt(dimension[0] + 1);
        int Y = rand.nextInt(dimension[1] + 1);

        return new int[]{X, Y};
    }

    public void Mover(int x, int y) {
        jugador.moverse(x, y);
        for (Entidad entidad : enemigos) {
            entidad.moverse(x, y);
            /*if (entidad.getTipoEntidad() == 2) {
                array. add entidad
            }*/
        }
        // si es robot2 se mueve segun colision
        /*Colision()
        for ( x array) {
            if enemigos.contains(x) {
                x movete
            }
        }*/
    }


    public void Colision() {
        for (Entidad enemigo1 : enemigos) {
            if (jugador.huboColision(enemigo1.getX(), enemigo1.getY())) {
                jugador.setVivo(false);
            }

            for (Entidad enemigo2 : enemigos) {
                if (enemigo1.huboColision(enemigo2.getX(), enemigo2.getY())) {
                    if (!(enemigo2 instanceof Explosion) || !(enemigo1 instanceof Explosion)) {
                        enemigos.add(new Explosion(enemigo1.getX(), enemigo1.getY()));
                        enemigos.remove(enemigo1);
                        enemigos.remove(enemigo2);
                    }
                }
            }
        }
    }

    public void TpAleatorio() {
        int[] coords = PosicionAleatoria();
        jugador.setX(coords[0]);
        jugador.setY(coords[1]);
    }

    public boolean HayTpSeguro() {
        return jugador.getTpSeguros() > 0;
    }

    public void TpSeguro(int x, int y) {
        jugador.setX(x);
        jugador.setY(y);
        jugador.setTpSeguros(jugador.getTpSeguros()-1);
    }

    public void Redimensionar(int x, int y) {
        dimension[0] = x;
        dimension[1] = y;
    }
}
