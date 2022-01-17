package ru.samcold.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ru.samcold.controllers.domain.MyDocument;

import java.io.IOException;

public class MainController {

    @FXML
    private Button btnTableRepair;

    @FXML
    void initialize() {
        btnTableRepair.setOnAction(actionEvent -> {
            try {
                MyDocument.getInstance().setTemplate();
                MyDocument.getInstance().save2();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
