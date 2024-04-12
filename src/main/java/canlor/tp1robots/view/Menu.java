package canlor.tp1robots.view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private MenuBar menuBar;
    private Button aceptar;

    public Menu() {
        menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        menuBar.setBackground(Background.fill(Color.rgb(243, 243, 243)));

        javafx.scene.control.Menu menu = createMenu();
        menuBar.getMenus().add(menu);
    }

    private javafx.scene.control.Menu createMenu() {
        javafx.scene.control.Menu menu = new javafx.scene.control.Menu("Opciones");

        MenuItem restartItem = new MenuItem("Reinciar");
        restartItem.setOnAction(e -> {
            // logica del restart
        });

        MenuItem dimensionItem = new MenuItem("Elegir dimension");
        dimensionItem.setOnAction(e -> {
            Stage stage = new Stage();

            VBox popupMenu = new VBox();
            popupMenu.setAlignment(Pos.CENTER);

            Text text = new Text("F x C");
            text.setFont(Font.font(15));
            // ------------------------------------
            HBox campos = new HBox();

            campos.setAlignment(Pos.CENTER);
            TextField filas = new TextField();
            filas.setPrefWidth(40);

            TextField columnas = new TextField();
            columnas.setPrefWidth(40);
            Text equis = new Text(" x ");
            equis.setFont(Font.font(15));

            campos.getChildren().addAll(filas, equis, columnas);
            // --------------------------------------

            HBox botones = new HBox();

            aceptar = new Button("Aceptar");

            Button cancelar = new Button("Cancelar");
            cancelar.setOnAction(event -> stage.close());

            botones.getChildren().addAll(cancelar, aceptar);
            botones.setAlignment(Pos.CENTER);
            botones.setSpacing(8);

            popupMenu.getChildren().addAll(text, campos, botones);

            Scene scene = new Scene(popupMenu, 160, 67);
            stage.setScene(scene);
            stage.show();
        });

        MenuItem salirItem = new MenuItem("Salir");
        salirItem.setOnAction(e -> Platform.exit());

        menu.getItems().addAll(restartItem, dimensionItem, salirItem);

        return menu;
    }

    private void crearEvento(EventHandler<ActionEvent> event) {
        aceptar.setOnAction(event);
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }
}