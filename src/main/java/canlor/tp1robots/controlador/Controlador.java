package canlor.tp1robots.controlador;

import canlor.tp1robots.modelo.juego.Juego;
import canlor.tp1robots.view.RobotsView;

public class Controlador {

    private final Juego modelo;
    private final RobotsView vista;
    private Eventos eventos;

    public Controlador(Juego modelo, RobotsView vista){
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

                if (valor1 >= 25 && valor2 >= 25) {
                    modelo.redimensionar(Integer.parseInt(espacio[0]), Integer.parseInt(espacio[1]));
                    modelo.reiniciar();
                    vista.redimensionar();
                } else {
                    vista.setErrorLabel("Ingrese numeros >=25");
                }
            } catch (NumberFormatException e) {
                vista.setErrorLabel("Ingrese valores numéricos");
            }

        });

        eventos.setTpAleatorio(_ -> {
            modelo.TpAleatorio();
            vista.actualizar();
        });

        /*eventos.setTpSeguro(event -> {
            modelo.TpSeguro();
            vista.actualizar();
        });*/

        eventos.setEsperar(_ -> {
            modelo.mover(modelo.getJugador().getX(), modelo.getJugador().getY());
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


        vista.crearEventos(eventos);
    }

    public void moverPersonaje() {
    }
}
