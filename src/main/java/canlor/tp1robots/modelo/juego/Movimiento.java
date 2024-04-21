package canlor.tp1robots.modelo.juego;

import canlor.tp1robots.modelo.entidades.Entidad;
import canlor.tp1robots.modelo.entidades.Explosion;
import canlor.tp1robots.modelo.entidades.Jugador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Clase que se encarga de mover a los enemigos y al jugador, y de verificar las colisiones.
 * Comprende al total de Juego. Se le delega los movimientos de las entidades del tablero.
 * Contiene una lista de enemigos, una referencia al jugador y al estado del juego.
 */
public class Movimiento {
    ArrayList<Entidad> enemigos;
    Jugador jugador;
    Estado estado;

    /**
     * Constructor de la instancia de Movimiento
     * @param enemigos ArrayList que contiene a los enemigos
     * @param jugador Entidad que representa al jugador
     * @param estado Instancia de Estado
     */
    public Movimiento(ArrayList<Entidad> enemigos, Jugador jugador, Estado estado) {
        this.enemigos = enemigos;
        this.jugador = jugador;
        this.estado = estado;
    }

    /**
     * Mueve al jugador y a los enemigos, antes chequea si las coordenadas son validas
     * @param coords coordenadas a las que mover al jugador
     */
    public void moverTodo(int[] coords) {
        if (posicionesValidas(coords)) {
            jugador(coords);
            robots();
        }
    }

    /**
     * Mueve a los enemigos y chequea las colisiones
     */
    public void robots() {
        for (Entidad entidad : enemigos) {
            entidad.moverse(jugador.getX(), jugador.getY(), enemigos);
        }
        colision();
    }

    /**
     * Mueve al jugador
     * @param coords coordenadas hacia las que mover al jugador
     */
    public void jugador(int[] coords) {
        jugador.moverse(coords[0], coords[1], enemigos);
    }

    /**
     * Le realiza un teletransporte al jugador y mueve a los enemigos hacia la nueva direccion
     * @param coords coordenadas a las que teletransportar al jugador
     */
    public void jugadorTP(int[] coords) {
        jugador.setX(coords[0]);
        jugador.setY(coords[1]);
        robots();
    }

    /**
     * Verifica las colisiones entre los enemigos y el jugador, y entre los enemigos entre si
     */
    private void colision() {
        List<Entidad> entidadesAQuitar = new ArrayList<>();
        List<Explosion> explosionesAniadir = new ArrayList<>();
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

                        explosionesAniadir.add(new Explosion(enemigo1.getX(), enemigo1.getY()));

                        posicionesExplosiones.add(posicionExplosion);
                    }
                }
            }
        }

        enemigos.removeAll(entidadesAQuitar);
        enemigos.addAll(explosionesAniadir);
    }

    /**
     * Verifica si las coordenadas son validas
     * @param coords coordenadas a verificar
     * @return true si las coordenadas son validas, false en caso contrario
     */
    private boolean posicionesValidas(int[] coords) {
        return coords[0] >= 0 && coords[1] >= 0 && coords[0] < estado.getDimension()[0] && coords[1] < estado.getDimension()[1];
    }
}
