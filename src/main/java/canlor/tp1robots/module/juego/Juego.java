package canlor.tp1robots.module.juego;

import canlor.tp1robots.module.entidades.*;

import java.util.ArrayList;
import java.util.Random;

public class Juego {
    final int[] cantRobotsInicial;
    private int[] dimension;
    private ArrayList<Entidad> entidades;
    private Jugador jugador;
    private int nivel = 0;

    public Juego(int filasTotales, int columnasTotales) {
        cantRobotsInicial = new int[]{4,2};
        dimension = new int[]{filasTotales, columnasTotales}; // a checkear
        nivel = 1;
        entidades = new ArrayList<>();
    }

    public void Iniciar() {
        jugador.setX(dimension[0]/2);
        jugador.setY(dimension[1]/2);
        entidades.add(jugador);

        for (int i = 0; i < cantRobotsInicial[0] * nivel; i++) {
            int[] coords = PosicionAleatoria();
            entidades.add(new Robot1(coords[0], coords[1]));
        }

        for (int i = 0; i < cantRobotsInicial[1] * nivel; i++) {
            int[] coords = PosicionAleatoria();
            entidades.add(new Robot2(coords[0], coords[1]));
        }
    }

    public boolean TerminoPartida() {
        boolean auxGano = true;
        boolean auxPerdio = true;
        for (Entidad entidad : entidades) {
            if (entidad.getTipoEntidad() == 1 || entidad.getTipoEntidad() == 2) {
                auxGano = false;
            } else if (entidad.getTipoEntidad() == 0) {
                auxPerdio = false;
            }
        }
        if (auxGano) {
            nivel +=1;
        }
        return auxGano || auxPerdio ;
    }

    public int[] PosicionAleatoria(){
        Random rand = new Random();

        int X = rand.nextInt(dimension[0] + 1);
        int Y = rand.nextInt(dimension[1] + 1);

        return new int[]{X, Y};
    }

    public void Mover() {

    }

    private void Colision() {

    }

    public void TpAleatorio() {
        int[] coords = PosicionAleatoria();
        jugador.setX(coords[0]);
        jugador.setY(coords[1]);
    }

    public void TpSeguro(int x, int y) {
        jugador.setX(x);
        jugador.setY(y);
    }

    public void Redimensionar(int x, int y) {
        dimension[0] = x;
        dimension[1] = y;
    }
}
