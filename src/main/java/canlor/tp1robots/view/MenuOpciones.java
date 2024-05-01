package canlor.tp1robots.view;

import canlor.tp1robots.controlador.Eventos;
import canlor.tp1robots.modelo.juego.Juego;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Representa el menu de opciones en el juego y muestra el nivel actual
 * Contiene metodos para crear el menu, manejar eventos,
 */
public class MenuOpciones {
    private final int[] COLOR_MENU = new int[]{245,243,243};

    private final Juego modelo;
    private final MenuBar menuBar;
    private final MenuItem restart;
    private final Button aceptar;
    private Menu nivel;
    private Menu puntos;
    private TextField filas;
    private TextField columnas;
    private Text errorLabel;
    private Stage stage;

    /**
     * Constructor para MenuOpciones, ademas muestra el nivel actual en la barra de menu
     * @param modelo modelo del juego que se esta jugando
     */
    public MenuOpciones(Juego modelo) {
        this.modelo = modelo;
        menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        menuBar.setBackground(Background.fill(Color.rgb(COLOR_MENU[0], COLOR_MENU[1], COLOR_MENU[2])));
        aceptar = new Button("Aceptar");
        restart = new MenuItem("Reinciar");

        Menu menu = createMenu();
        menuBar.getMenus().add(menu);

        nivel = new Menu("Nivel: " + modelo.getNivel());
        puntos = new Menu("Puntos: " + modelo.getPuntosJugador());

        menuBar.getMenus().addAll(nivel, puntos);
    }

    /**
     * Crea el menu de opciones
     * @return Menu devuelve el menu creado
     */
    private Menu createMenu() {
        Menu menu = new Menu("Opciones");

        MenuItem dimensionItem = new MenuItem("Elegir dimension");
        dimensionItem.setOnAction(_ -> {
            stage = new Stage();

            VBox popupMenu = new VBox();
            popupMenu.setAlignment(Pos.CENTER);

            Text text = new Text("F x C");
            text.setFont(Font.font(15));
            // ----------------------------

            HBox campos = new HBox();

            campos.setAlignment(Pos.CENTER);
            filas = new TextField();
            filas.setPrefWidth(50);
            filas.setPromptText(">=10");

            columnas = new TextField();
            columnas.setPrefWidth(50);
            columnas.setPromptText(">=10");
            Text X = new Text(" x ");
            X.setFont(Font.font(15));

            campos.getChildren().addAll(filas, X, columnas);
            // ----------------------------

            errorLabel = new Text("");
            errorLabel.setFont(Font.font(15));
            // ----------------------------

            HBox botones = new HBox();

            Button cancelar = new Button("Cancelar");
            cancelar.setOnAction(_ -> cerrarVentana());

            botones.getChildren().addAll(cancelar, aceptar);
            botones.setAlignment(Pos.CENTER);
            botones.setSpacing(8);

            popupMenu.getChildren().addAll(text, campos, errorLabel, botones);

            Scene scene = new Scene(popupMenu, 180, 100);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        });

        MenuItem salirItem = new MenuItem("Salir");
        salirItem.setOnAction(_ -> Platform.exit());

        menu.getItems().addAll(restart, dimensionItem, salirItem);

        return menu;
    }

    /**
     * Crea eventos para el menu de opciones
     * @param eventos eventos que se van a crear
     */
    public void crearEvento(Eventos eventos) {
        aceptar.setOnAction(eventos.getRedimensionar());
        restart.setOnAction(eventos.getReiniciar());
    }

    /**
     * Cierra la ventana de dimensiones
     */
    public void cerrarVentana() {
        stage.close();
    }

    /**
     * Devuelve el boton aceptar
     * @param error mensaje de error
     */
    public void setErrorLabel(String error) {
        errorLabel.setText(error);
    }

    /**
     * Setea la etiqueta de nivel con el nivel actual
     */
    public void setNivelLabel() {
        menuBar.getMenus().remove(nivel);
        this.nivel = new Menu("Nivel: " + modelo.getNivel());
        menuBar.getMenus().add(nivel);
    }

    /**
     * Devuelve el menuBar
     * @return MenuBar menuBar
     */
    public MenuBar getMenuBar() {
        return menuBar;
    }

    /**
     * Devuelve las dimensiones ingresadas
     * @return String[] dimensiones ingresadas
     */
    public String[] getRedimensiones() {
        return new String[]{filas.getText(), columnas.getText()};
    }

    public void updatePuntos() {
        menuBar.getMenus().remove(puntos);
        this.puntos = new Menu("Puntos: " + modelo.getPuntosJugador());
        menuBar.getMenus().add(puntos);
    }

}