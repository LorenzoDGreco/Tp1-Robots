package canlor.tp1robots.controlador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Eventos {
    private EventHandler<ActionEvent> redimensionar;
    private EventHandler<ActionEvent> tpAleatorio;
    private EventHandler<ActionEvent> tpSeguro;
    private EventHandler<ActionEvent> esperar;
    private EventHandler<ActionEvent> reiniciar;
    private EventHandler<MouseEvent> mouseClick;
    private EventHandler<ActionEvent> teclado;


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

    public EventHandler<ActionEvent> getTeclado() {
        return teclado;
    }

    public void setTeclado(EventHandler<ActionEvent> teclado) {
        this.teclado = teclado;
    }
}
