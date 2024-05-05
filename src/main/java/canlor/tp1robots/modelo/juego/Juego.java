package canlor.tp1robots.modelo.juego;

import canlor.tp1robots.modelo.entidades.Entidad;
import canlor.tp1robots.modelo.entidades.Jugador;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Represemta la instancia de Juego  del juego Robots
 * Condensa los metodos para iniciar el juego, reiniciarlo, redimensionarlo, mover al jugador y realizar teletransportes
 * Contiene instancias que se encargan del Estado del juego, de los movimientos y de los teletransportes.
 * Ademas inicializa a la Entidad Jugador y a un ArrayList que contiene a los enemigos
 */
public class Juego {
    private final int TILE = 16;
    private final int MENU = 20;
    private final int BOTONES = 165;

    private Estado estado;
    private Movimiento movimiento;
    private Tps tps;

    private final Jugador jugador;
    private ArrayList<Entidad> enemigos;

    /**
     * Constructor de la instancia de Juego
     * @param filas cantidad de filas iniciales para la dimension del tablero
     * @param columnas cantidad de columnas iniciales para la dimension del tablero
     */
    public Juego(int filas, int columnas) {
        jugador = new Jugador(filas/2, columnas/2);
        enemigos = new ArrayList<>();

        tps = new Tps();
        estado = new Estado(enemigos, jugador, tps, filas, columnas);
        movimiento = new Movimiento(enemigos, jugador, estado);
    }

    /**
     * Inicia el juego delegando la tarea al Estado
     */
    public void iniciar() {
        estado.iniciar();
    }

    /**
     * Reinicia el juego delegando la tarea al Estado
     */
    public void reiniciar() {
        estado.reiniciar();
    }

    /**
     * Redimensiona el tablero delegando la tarea al Estado
     * @param filas cantidad de filas a las que redimensionar
     * @param columnas cantidad de columnas a las que redimensionar
     */
    public void redimensionar(int filas, int columnas) {
        estado.redimensionar(filas, columnas);
    }

    /**
     * Si no se termino la partida, se mueve al jugador a las coordenadas especificas si esta
     * activo el teletransporte seguro o mueve a todos los elementos del juego si no lo esta.
     * @param x coordenada x a la que mover al jugador
     * @param y coordenada y a la que mover al jugador
     */
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

    /**
     * Realiza un teletransporte aleatorio si no se termino la partida
     */
    public void tpAleatorio(){
        estado.comprobarEstadoPartida(() -> {
            tps.setTpSeguro(false);
            int[] coords = tps.tpAleatorio(estado.getDimension());
            movimiento.jugadorTP(coords);
        });
    }

    /**
     * Realiza un teletransporte seguro delegando la tarea a Tps
     */
    public void tpSeguro() {
        tps.tpSeguro(jugador.getTpSeguros());
    }

    /**
     * Cambia la imagen del jugador y de los enemigos
     */
    public void cambiarImagen() {
        jugador.cambiarImagen();
        for(Entidad entidad : enemigos) { entidad.cambiarImagen();}
    }

    /**
     * Devuelve el ArrayList de enemigos
     * @return ArrayList de enemigos
     */
    public ArrayList<Entidad> getEnemigos() {
        return enemigos;
    }

    /**
     * Devuelve la cantidad de teletransportes seguros que tiene el jugador
     * @return int cantidad de teletransportes seguros disponibles
     */
    public int getTpSeguros() {
        return jugador.getTpSeguros();
    }

    /**
     * Devuelve la dimension del tablero
     * @return int[] dimension del tablero
     */
    public int[] getDimension() {
        return estado.getDimension();
    }

    /**
     * Devuelve el tamanio total de la ventana, incluyendo el tablero y los margenes
     * @return int[] (ancho y alto) tamanio total de la ventana
     */
    public int[] getTamanioTotal() {
        int width = TILE + (estado.getDimension()[1] * TILE);
        int height = MENU + BOTONES + (estado.getDimension()[0] * TILE);
        return new int[]{width, height};
    }

    /**
     * Devuelve el nivel actual del juego
     * @return int nivel actual del juego
     */
    public int getNivel() {
        return estado.getNivel();
    }

    /**
     * Devuelva la coordenada X actual del jugador
     * @return int coordenada X del jugador
     */
    public int getJugadorX() {
        return jugador.getX();
    }

    /**
     * Devuelva la coordenada Y actual del jugador
     * @return int coordenada Y del jugador
     */
    public int getJugadorY() {
        return jugador.getY();
    }

    /**
     * Devuelve la imagen del jugador
     * @return BufferedImage imagen del jugador
     */
    public BufferedImage getImagenJugador() {
        return jugador.getImagen();
    }

    public int getPuntosJugador() {
        return jugador.getPuntos();
    }

    public boolean getTerminoPartida() {
        estado.terminoPartida();
        return estado.getEstadoPartida();
    }
}
