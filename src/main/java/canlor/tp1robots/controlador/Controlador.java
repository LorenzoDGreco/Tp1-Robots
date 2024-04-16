package canlor.tp1robots.controlador;

import canlor.tp1robots.modelo.juego.Juego;
import canlor.tp1robots.view.RobotsView;
import javafx.scene.input.KeyCode;

public class Controlador {

    private final Juego modelo;
    private final RobotsView vista;
    private Eventos eventos;

    public Controlador(Juego modelo, RobotsView vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        modelo.iniciar();
        vista.actualizar();

        eventos = new Eventos();

        eventos.setRedimensionar(_ -> {
            String[] espacio = vista.getRedimensiones();
            try {
                int valor1 = Integer.parseInt(espacio[0]);
                int valor2 = Integer.parseInt(espacio[1]);
                if (valor1 < 10 || valor2 < 10) {
                    vista.setErrorLabel("Ingrese valores >=10");
                    return;
                }
                modelo.redimensionar(Integer.parseInt(espacio[0]), Integer.parseInt(espacio[1]));
                modelo.reiniciar();
                vista.redimensionar();
            } catch (NumberFormatException e) {
                vista.setErrorLabel("Ingrese valores numéricos");
            }

        });

        eventos.setTpAleatorio(_ -> {
            modelo.TpAleatorio();
            vista.actualizar();
        });


        eventos.setTpSeguro(_ -> {
            modelo.activarTpSeguro();
        });

        eventos.setEsperar(_ -> {
            modelo.mover(modelo.getJugadorX(), modelo.getJugadorY());
            vista.actualizar();
        });

        eventos.setReiniciar(_ -> {
            modelo.reiniciar();
            vista.actualizar();
        });

        eventos.setMouseClick(e -> {
            int columna = (int) (e.getX() / 16);
            int fila = (int) (e.getY() / 16);

            modelo.mover(fila, columna);
            vista.actualizar();
        });

        eventos.setTeclado(event -> {
            KeyCode key = event.getCode();
            switch (key) {
                case W:
                    modelo.mover(modelo.getJugadorX() - 1, modelo.getJugadorY());
                    break;
                case Q:
                    modelo.mover(modelo.getJugadorX() - 1, modelo.getJugadorY() - 1);
                    break;
                case E:
                    modelo.mover(modelo.getJugadorX() - 1, modelo.getJugadorY() + 1);
                    break;
                case A:
                    modelo.mover(modelo.getJugadorX(), modelo.getJugadorY() - 1);
                    break;
                case S:
                    modelo.mover(modelo.getJugadorX(), modelo.getJugadorY());
                    break;
                case D:
                    modelo.mover(modelo.getJugadorX(), modelo.getJugadorY() + 1);
                    break;
                case Z:
                    modelo.mover(modelo.getJugadorX() + 1, modelo.getJugadorY() - 1);
                    break;
                case X:
                    modelo.mover(modelo.getJugadorX() + 1, modelo.getJugadorY());
                    break;
                case C:
                    modelo.mover(modelo.getJugadorX() + 1, modelo.getJugadorY() + 1);
                    break;
                case R:
                    modelo.reiniciar();
                    break;
            }
            vista.actualizar();

        });

        vista.crearEventos(eventos);
    }
}
