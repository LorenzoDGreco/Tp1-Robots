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
     * Verifica las colisiones entre los enemigos y el jugador, y de los enemigos entre si
     */
    private void colision() {
        List<Entidad> entidadesAQuitar = new ArrayList<>();
        List<Entidad> explosionesAniadir = new ArrayList<>();
        int nuevos_puntos = 0;

        chequearColisionJugadorEnemigo();
        chequearColisionEnemigoEnemigo(entidadesAQuitar, explosionesAniadir);

        for (Entidad enemigo : entidadesAQuitar) {
            nuevos_puntos += enemigo.getPuntosQueDa();
        }
        jugador.addPuntos(nuevos_puntos);
        enemigos.removeAll(entidadesAQuitar);
        enemigos.addAll(explosionesAniadir);
    }

    /**
     * Chequea si hubo colision entre Jugador y algun enemigo
     * Si hubo una colision con el jugador, lo desactiva
     */
    private void chequearColisionJugadorEnemigo() {
        for (Entidad enemigo : enemigos) {
            if (jugador.huboColision(enemigo.getX(), enemigo.getY())) {
                jugador.setActivo(false);
                jugador.resetPuntos();
            }
        }
    }

    /**
     * Chequea colisiones entre enemigos.
     * Si hubo una colision se guarda la posicion donde ocurrio
     * @param entidadesAQuitar
     * @param explosionesAniadir
     */
    private void chequearColisionEnemigoEnemigo(List<Entidad> entidadesAQuitar, List<Entidad> explosionesAniadir) {
        List<String> posicionesExplosiones = new ArrayList<>();
        for (int i = 0; i < enemigos.size(); i++) {
            Entidad enemigo1 = enemigos.get(i);
            for (int j = i + 1; j < enemigos.size(); j++) {
                Entidad enemigo2 = enemigos.get(j);
                if (enemigo1.huboColision(enemigo2.getX(), enemigo2.getY())) {
                    String pos = enemigo1.getX() + "," + enemigo2.getY();
                    chequearNuevaExplosion(enemigo1, enemigo2, posicionesExplosiones, entidadesAQuitar, explosionesAniadir, pos);
                }
            }
        }
    }

    /**
     * Agrega una nueva explosion si fue una colision robot-robot
     * @param enemigo1 Entidad enemiga que colisiono
     * @param enemigo2 Entidad enemiga que colisiono
     * @param posicionesExplosiones String[] posiciones donde ocurrio una explosion
     * @param entidadesAQuitar array de entidades a eliminar (entidades que colisionaron)
     * @param explosionesAniadir array de explosiones a aniadir (nuevas explosiones que aniadir a this.enemigos)
     * @param pos posicion donde se chequea por la nueva explosion
     */
    private void chequearNuevaExplosion(Entidad enemigo1, Entidad enemigo2, List<String> posicionesExplosiones, List<Entidad> entidadesAQuitar,List<Entidad> explosionesAniadir, String pos) {
        if (!posicionesExplosiones.contains(pos)) { // si la explosion es nueva (no fue colision robot-explosion)
            entidadesAQuitar.add(enemigo1);
            entidadesAQuitar.add(enemigo2);
            explosionesAniadir.add(new Explosion(enemigo1.getX(), enemigo2.getY()));
            posicionesExplosiones.add(pos);
        }
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
