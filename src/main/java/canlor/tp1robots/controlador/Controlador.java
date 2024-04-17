package canlor.tp1robots.controlador;

import canlor.tp1robots.modelo.juego.Juego;
import canlor.tp1robots.view.RobotsView;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;

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

        eventos.setTimer(new TimerTask() {
            @Override
            public void run() {
                modelo.cambiarImagen();

                Platform.runLater(() -> vista.actualizar());

            }
        });

        vista.crearEventos(eventos);
    }
}

/*
public class Main extends Application {
    private Timer timer;
    private Label label;

    @Override
    public void start(Stage primaryStage) {
        label = new Label("0");
        StackPane root = new StackPane(label);
        Scene scene = new Scene(root, 200, 100);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Timer en JavaFX");
        primaryStage.show();

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int counter = 0;

            @Override
            public void run() {
                // Incrementar el contador
                counter++;

                // Actualizar la interfaz de usuario
                Platform.runLater(() -> label.setText(String.valueOf(counter)));
            }
        }, 0, 500); // Ejecutar cada segundo
    }

    @Override
    public void stop() {
        // Detener el timer cuando la aplicación se cierra
        timer.cancel();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


 */