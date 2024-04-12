package canlor.tp1robots.controlador;

import canlor.tp1robots.module.juego.Juego;
import canlor.tp1robots.view.RobotsView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;

public class Controlador {

    private final Juego modelo;
    private final RobotsView vista;
    private ArrayList<EventHandler<ActionEvent>> eventos;

    public Controlador(Juego modelo, RobotsView vista){
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        modelo.iniciar();
        vista.actualizar();

        Eventos eventos = new Eventos();

        eventos.setRedimensionar(event -> {
            String[] espacio = vista.getRedimensiones();
            modelo.redimensionar(Integer.parseInt(espacio[0]), Integer.parseInt(espacio[1]));
        });

        eventos.setTpAleatorio(event -> {
                modelo.TpAleatorio();
        });

        /*EventHandler<ActionEvent> EventoTpSeguro = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                modelo.TpSeguro();
            }
        };*/



    }

    public void moverPersonaje() {
    }
}
