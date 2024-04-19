package canlor.tp1robots.controlador;

import canlor.tp1robots.modelo.juego.Juego;
import canlor.tp1robots.view.RobotsView;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.TimerTask;

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
            eventoRedimensionar();
        });

        eventos.setTpAleatorio(_ -> {
            modelo.tpAleatorio();
            vista.actualizar();
        });


        eventos.setTpSeguro(_ -> {
            modelo.tpSeguro();
        });

        eventos.setEsperar(_ -> {
            modelo.mover(modelo.getJugadorX(), modelo.getJugadorY());
            vista.actualizar();
        });

        eventos.setReiniciar(_ -> {
            modelo.reiniciar();
            vista.actualizar();
        });

        eventos.setMouseClick(this::eventoMouseClick);

        eventos.setTeclado(this::eventoTeclado);

        eventos.setTimer(new TimerTask() {
            @Override
            public void run() {
                modelo.cambiarImagen();
                Platform.runLater(vista::actualizarAnimaciones);
            }
        });

        vista.crearEventos(eventos);
    }

    public void eventoRedimensionar() {
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
            vista.actualizar();
        } catch (NumberFormatException e) {
            vista.setErrorLabel("Ingrese valores numéricos");
        }
    }

    private void eventoMouseClick(MouseEvent e) {
        int columna = (int) (e.getX() / 16);
        int fila = (int) (e.getY() / 16);

        modelo.mover(fila, columna);
        vista.actualizar();
    }

    private void eventoTeclado(KeyEvent event) {
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
    }
}

