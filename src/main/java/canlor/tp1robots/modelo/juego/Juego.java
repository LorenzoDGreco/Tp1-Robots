package canlor.tp1robots.modelo.juego;

import canlor.tp1robots.modelo.entidades.*;

import java.util.*;

public class Juego {
    final int[] cantRobotsInicial;
    private final int[] dimension;
    private ArrayList<Entidad> enemigos;
    private final Jugador jugador;
    private int nivel;
    private boolean tpSeguroActivado;

    public Juego(int filas, int columnas) {
        cantRobotsInicial = new int[]{4,2};
        dimension = new int[]{filas, columnas};
        tpSeguroActivado = false;
        nivel = 1;
        enemigos = new ArrayList<>();
        jugador = new Jugador(filas/2, columnas/2);
    }

    public void reiniciar() {
        tpSeguroActivado = false;
        nivel = 1;
        enemigos = new ArrayList<>();
        jugador.setTpSeguros(1);
        iniciar();
    }

    public void iniciar() {
        tpSeguroActivado = false;
        jugador.setX(dimension[0]/2);
        jugador.setY(dimension[1]/2);
        jugador.setActivo(true);

        for (int i = 0; i < cantRobotsInicial[0] * nivel; i++) {
            int[] coords = PosicionAleatoria();
            enemigos.add(new Robot1(coords[1], coords[0]));
        }

        for (int i = 0; i < cantRobotsInicial[1] * nivel; i++) {
            int[] coords = PosicionAleatoria();
            enemigos.add(new Robot2(coords[1], coords[0]));
        }
    }

    public boolean TerminoPartida() {
        if (jugador.isActivo()) {
            return false;
        }

        boolean gano = true;
        for (Entidad enemigo : enemigos) {
            if (enemigo.getTipoEntidad() == 1 || enemigo.getTipoEntidad() == 2) {
                gano = false;
                break;
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
        if (tpSeguroActivado) {
            TpSeguro(x, y);
        } else {
            moverJugador(x, y);
            moverRobots();
        }
    }

    private void moverJugador(int x, int y) {
        jugador.moverse(x, y, enemigos);
        Colision();
    }

    private void moverRobots() {
        for (Entidad entidad : enemigos) {
            entidad.moverse(jugador.getX(), jugador.getY(), enemigos);
        }
        Colision();
    }

    private void Colision() {
        List<Entidad> entidadesAQuitar = new ArrayList<>();
        List<Explosion> explosionesAñadir = new ArrayList<>();
        Set<String> posicionesExplosiones = new HashSet<>();

        for (int i = 0; i < enemigos.size(); i++) {
            Entidad enemigo1 = enemigos.get(i);

            if (jugador.huboColision(enemigo1.getX(), enemigo1.getY())) {
                jugador.setActivo(false);
            }

            for (int j = i + 1; j < enemigos.size(); j++) {
                Entidad enemigo2 = enemigos.get(j);

                if (enemigo1.huboColision(enemigo2.getX(), enemigo2.getY())) {
                    String posicionExplosion = enemigo1.getX() + "," + enemigo1.getY();

                    if (!posicionesExplosiones.contains(posicionExplosion)) {

                        entidadesAQuitar.add(enemigo1);
                        entidadesAQuitar.add(enemigo2);

                        explosionesAñadir.add(new Explosion(enemigo1.getX(), enemigo1.getY()));

                        posicionesExplosiones.add(posicionExplosion);
                    }
                }
            }
        }

        enemigos.removeAll(entidadesAQuitar);
        enemigos.addAll(explosionesAñadir);
    }



    public void TpAleatorio() {
        int[] coords = PosicionAleatoria();
        jugador.setY(coords[0]);
        jugador.setX(coords[1]);
        moverRobots();
    }

    private boolean hayTpSeguro() {
        return jugador.getTpSeguros() > 0;
    }

    public void TpSeguro(int x, int y) {
        if (hayTpSeguro()) {
            jugador.setX(x);
            jugador.setY(y);
            jugador.setTpSeguros(jugador.getTpSeguros()-1);
            tpSeguroActivado = false;
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

    public int[] getDimension() {
        return dimension;
    }

    public int[] getTamanioTotal() {
        int width = 16 + dimension[1] * 16;
        int height = 20 + 165 + (dimension[0] * 16);
        return new int[]{width, height};
    }

    public void activarTpSeguro() {
        if (hayTpSeguro()) {
            tpSeguroActivado = true;
        }
    }

    public int getJugadorX() {
        return jugador.getX();
    }

    public int getJugadorY() {
        return jugador.getY();
    }
}
