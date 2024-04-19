package canlor.tp1robots.modelo.juego;

import canlor.tp1robots.modelo.entidades.Entidad;
import canlor.tp1robots.modelo.entidades.Explosion;
import canlor.tp1robots.modelo.entidades.Jugador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Movimiento {
    ArrayList<Entidad> enemigos;
    Jugador jugador;
    Estado estado;

    public Movimiento(ArrayList<Entidad> enemigos, Jugador jugador, Estado estado) {
        this.enemigos = enemigos;
        this.jugador = jugador;
        this.estado = estado;
    }

    public void moverTodo(int[] coords) {
        if (posicionesValidas(coords)) {
            jugador(coords);
            robots();
        }
    }

    public void robots() {
        for (Entidad entidad : enemigos) {
            entidad.moverse(jugador.getX(), jugador.getY(), enemigos);
        }
        colision();
    }

    public void jugador(int[] coords) {
        jugador.moverse(coords[0], coords[1], enemigos);
    }

    public void jugadorTP(int[] coords) {
        jugador.setX(coords[0]);
        jugador.setY(coords[1]);
        robots();
    }

    public void colision() {
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

    private boolean posicionesValidas(int[] coords) {
        return coords[0] >= 0 && coords[1] >= 0 && coords[0] < estado.getDimension()[0] && coords[1] < estado.getDimension()[1];
    }
}
