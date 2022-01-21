package ru.samcold.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import ru.samcold.Main;

import java.util.Objects;

public class AlertManager {
    // region sinleton
    private static AlertManager instance;

    private AlertManager() {
    }

    public static AlertManager getInstance() {
        if (instance == null) {
            instance = new AlertManager();
        }
        return instance;
    }
    // endregion

    private boolean dialogResult = false;

    public void notFoundRTK() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("");
        alert.setContentText("Номер РТК не найден");
        alert.setHeaderText("");

        DialogPane pane = alert.getDialogPane();
        pane.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/styles/style.css")).toExternalForm());

        alert.showAndWait();
    }

    public boolean showDeleteInfo(String text) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение удаления");
        alert.setContentText(text);
        alert.setHeaderText("");

        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("OK");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setDefaultButton(false);
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Отмена");
        ((Button) alert.getDialogPane().lookupButton(ButtonType.CANCEL)).setDefaultButton(true);

        DialogPane pane = alert.getDialogPane();
        pane.getStylesheets().add(Objects.requireNonNull(Main.class.getResource("/styles/style.css")).toExternalForm());

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                dialogResult = true;
            }
        });

        return dialogResult;
    }
}
