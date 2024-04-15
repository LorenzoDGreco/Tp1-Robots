package canlor.tp1robots.view;

import canlor.tp1robots.controlador.Eventos;
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

public class Menu {
    private final MenuBar menuBar;
    private final MenuItem restart;
    private final Button aceptar;
    private TextField filas;
    private TextField columnas;
    private Text errorLabel;
    private Stage stage;

    public Menu() {
        menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        menuBar.setBackground(Background.fill(Color.rgb(243, 243, 243)));
        aceptar = new Button("Aceptar");
        restart = new MenuItem("Reinciar");

        javafx.scene.control.Menu menu = createMenu();
        menuBar.getMenus().add(menu);
    }

    private javafx.scene.control.Menu createMenu() {
        javafx.scene.control.Menu menu = new javafx.scene.control.Menu("Opciones");



        MenuItem dimensionItem = new MenuItem("Elegir dimension");
        dimensionItem.setOnAction(e -> {
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
            filas.setPromptText(">=25");

            columnas = new TextField();
            columnas.setPrefWidth(50);
            columnas.setPromptText(">=25");
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

    public void crearEvento(Eventos eventos) {
        aceptar.setOnAction(eventos.getRedimensionar());
        restart.setOnAction(eventos.getReiniciar());
    }

    public void cerrarVentana(){
        stage.close();
    }

    public void setErrorLabel(String error) {
        errorLabel.setText(error);
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public String[] getRedimensiones() {
        return new String[]{filas.getText(), columnas.getText()};
    }
}