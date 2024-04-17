package canlor.tp1robots.controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.TimerTask;

/**
 * Representa los handlers de los eventos para la aplicacion
 * Contiene handlers para los eventos que puedan sucedes: redimensionar, teleportar aleatoriamente, teleportar seguro, esperar, reiniciar, click del mouse, teclado y timer
 */
public class Eventos {
    private EventHandler<ActionEvent> redimensionar;
    private EventHandler<ActionEvent> tpAleatorio;
    private EventHandler<ActionEvent> tpSeguro;
    private EventHandler<ActionEvent> esperar;
    private EventHandler<ActionEvent> reiniciar;
    private EventHandler<MouseEvent> mouseClick;
    private EventHandler<KeyEvent> teclado;
    private TimerTask timer;


    public EventHandler<ActionEvent> getRedimensionar() {
        return redimensionar;
    }

    public void setRedimensionar(EventHandler<ActionEvent> redimensionar) {
        this.redimensionar = redimensionar;
    }

    public EventHandler<ActionEvent> getTpAleatorio() {
        return tpAleatorio;
    }

    public void setTpAleatorio(EventHandler<ActionEvent> tpAleatorio) {
        this.tpAleatorio = tpAleatorio;
    }

    public EventHandler<ActionEvent> getTpSeguro() {
        return tpSeguro;
    }

    public void setTpSeguro(EventHandler<ActionEvent> tpSeguro) {
        this.tpSeguro = tpSeguro;
    }

    public EventHandler<ActionEvent> getEsperar() {
        return esperar;
    }

    public void setEsperar(EventHandler<ActionEvent> esperar) {
        this.esperar = esperar;
    }

    public EventHandler<ActionEvent> getReiniciar() {
        return reiniciar;
    }

    public void setReiniciar(EventHandler<ActionEvent> reiniciar) {
        this.reiniciar = reiniciar;
    }

    public EventHandler<MouseEvent> getMouseClick() {
        return mouseClick;
    }

    public void setMouseClick(EventHandler<MouseEvent> mouse) {
        this.mouseClick = mouse;
    }

    public EventHandler<KeyEvent> getTeclado() {
        return teclado;
    }

    public void setTeclado(EventHandler<KeyEvent> teclado) {
        this.teclado = teclado;
    }

    public TimerTask getTimer() {
        return timer;
    }

    public void setTimer(TimerTask timer) {
        this.timer = timer;
    }


}
