package canlor.tp1robots.controlador;

import canlor.tp1robots.modelo.juego.Juego;
import canlor.tp1robots.view.RobotsView;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.TimerTask;

/**
 * Representa al controllador del juego Robots
 * Gestiona cambios en el modelo y en la vista dados por el input del usuario
 */
public class Controlador {

    private final Juego modelo;
    private final RobotsView vista;
    private boolean bucleEsperar;

    /**
     * Constructor para el controlador
     * @param modelo modelo del juego iniciado
     * @param vista vista del juego iniciado
     */
    public Controlador(Juego modelo, RobotsView vista) {
        this.modelo = modelo;
        this.vista = vista;
        bucleEsperar = false;
    }

    /**
     * Maneja el evento del TpAleatorio
     */
    public void tpAleatorio() {
        if (bucleEsperar) { return; }

        modelo.tpAleatorio();
        vista.actualizar();
    }

    /**
     * Maneja el evento del TpSeguro
     */
    public void tpSeguro() {
        if (bucleEsperar) { return; }

        modelo.tpSeguro();
    }

    /**
     * Maneja el evento de esperar a los robots
     */
    public void esperar() {
        Task task = new Task() {
            @Override
            protected Void call() throws Exception {
                while (!modelo.getTerminoPartida()) {
                    modelo.mover(modelo.getJugadorX(), modelo.getJugadorY());
                    Thread.sleep(300);
                    Platform.runLater(vista::actualizar);
                }
                bucleEsperar = false;
                return null;
            }
        };
        bucleEsperar = true;
        Thread thread = new Thread(task);
        thread.start();
    }

    /**
     * Maneja el evento de reiniciar la partida
     */
    public void reiniciar() {
        modelo.reiniciar();
        vista.actualizar();
    }

    /**
     * Maneja evento de redimensionar el tablero
     */
    public void redimensionar() {
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

    /**
     * Maneja evento de click del mouse
     * @param e MouseEvent de click del mouse
     */
    public void mouseClick(MouseEvent e) {
        if (bucleEsperar) { return; }

        int columna = (int) (e.getX() / 16);
        int fila = (int) (e.getY() / 16);

        modelo.mover(fila, columna);
        vista.actualizar();
    }

    /**
     * Maneja evento de teclado
     * @param event KeyEvent de teclado
     */
    public void teclado(KeyEvent event) {
        if (bucleEsperar) { return; }

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

    /**
     * Crea el evento para el timer que controla las animaciones
     * @return TimerTask
     */
    public TimerTask timer() {
        return new TimerTask() {
            @Override
            public void run() {
                modelo.cambiarImagen();
                Platform.runLater(vista::actualizarAnimaciones);
            }
        };
    }
}

