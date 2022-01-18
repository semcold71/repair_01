package ru.samcold.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ru.samcold.domain.Customer;
import ru.samcold.domain.MyDocument;
import ru.samcold.repairing.Repair;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainController {

    @FXML
    private Button btn_LoadTemplate;

    @FXML
    private Button btn_ExtractCustomer;

    private final MyDocument myDocument = MyDocument.getInstance();
    private final Repair repair = Repair.getInstance();
    private final BooleanProperty isLoaded = new SimpleBooleanProperty();

    private Customer customer;

    @FXML
    void initialize() {
        isLoaded.set(false);
        btn_LoadTemplate.setOnAction(actionEvent -> openTemplate());
        btn_ExtractCustomer.setOnAction(actionEvent -> extractCustomer());
        btn_ExtractCustomer.disableProperty().bind(isLoaded.not());
    }

    private void openTemplate() {
        String path;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выбрать документ для редактирования");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Microsoft Word (.docx)", "*.docx"));
        File res = fileChooser.showOpenDialog(new Stage());

        if (res != null) {
            path = res.getAbsolutePath();
        } else return;

        try {
            myDocument.setTemplate(path);
            isLoaded.set(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void extractCustomer() {
        try {
            this.customer = repair.extractCustomer();
            System.out.println(customer);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
