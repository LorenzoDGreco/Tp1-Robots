package canlor.tp1robots.controlador;

import canlor.tp1robots.module.juego.Juego;
import canlor.tp1robots.view.RobotsView;
import javafx.event.EventHandler;

import java.util.ArrayList;

public class Controlador {

    private final Juego modelo;
    private final RobotsView vista;
    private ArrayList<EventHandler> eventos;

    public Controlador(Juego modelo, RobotsView vista){
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        modelo.iniciar();
        vista.actualizar();
    }

    public void moverPersonaje() {
    }
}
