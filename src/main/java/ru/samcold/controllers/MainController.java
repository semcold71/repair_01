package ru.samcold.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.validation.ValidationSupport;
import ru.samcold.domain.Customer;
import ru.samcold.domain.MyDocument;
import ru.samcold.repairing.Repair;

import java.io.File;
import java.io.IOException;

public class MainController {

    //region FXML
    @FXML
    private Button btn_ExtractCrane;

    @FXML
    private Button btn_ExtractCustomer;

    @FXML
    private Button btn_LoadTemplate;

    @FXML
    private TextField txt_CustomerAddress;

    @FXML
    private TextField txt_CustomerBoss;

    @FXML
    private TextField txt_CustomerCity;

    @FXML
    private TextField txt_CustomerName;

    @FXML
    private TextField txt_CustomerPhone;

    @FXML
    private TextField txt_CustomerPost;

    @FXML
    private TextField txt_CustomerRegion;

    @FXML
    private TextField txt_CustomerZip;
    //endregion

    private final MyDocument myDocument = MyDocument.getInstance();
    private final Repair repair = Repair.getInstance();
    private final BooleanProperty isLoaded = new SimpleBooleanProperty();

    private Customer customer;
    private ValidationSupport validationSupport;

    @FXML
    void initialize() {
        isLoaded.set(false);
        validationSupport = new ValidationSupport();
        initFields();
        initButtons();
    }

    private void initFields() {

    }

    private void initButtons() {
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

    private void bindingData() {

    }

}
