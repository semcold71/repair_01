package ru.samcold.controllers;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import ru.samcold.domain.MyDocument;
import ru.samcold.domain.Customer;
import ru.samcold.repairing.Repair;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainController {

    //region FXML
    @FXML
    private TitledPane pane_Customer;
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
        customer = new Customer();
        validationSupport = new ValidationSupport();
        isLoaded.set(false);
        btn_ExtractCustomer.disableProperty().bind(isLoaded.not());

        initCustomerFields();
        initButtons();
    }

    private void initCustomerFields() {
        // bind
        txt_CustomerName.textProperty().bindBidirectional(customer.nameProperty());
        txt_CustomerZip.textProperty().bindBidirectional(customer.zipProperty());
        txt_CustomerRegion.textProperty().bindBidirectional(customer.regionProperty());
        txt_CustomerCity.textProperty().bindBidirectional(customer.cityProperty());
        txt_CustomerAddress.textProperty().bindBidirectional(customer.addressProperty());
        txt_CustomerBoss.textProperty().bindBidirectional(customer.bossProperty());
        txt_CustomerPost.textProperty().bindBidirectional(customer.postProperty());
        txt_CustomerPhone.textProperty().bindBidirectional(customer.phoneProperty());

        // validator
        List<Node> list = ((Pane) pane_Customer.getContent()).getChildren();
        setValidation(list);
    }

    private void initCraneFields() {

    }

    private void setValidation(List<Node> nodeList) {
        for (Node node : nodeList) {
            if (node instanceof TextField) {
                validationSupport.registerValidator((Control) node, Validator.createEmptyValidator("Необходимо заполнить", Severity.ERROR));
            } else if (node instanceof Pane) {
                setValidation(((Pane) node).getChildren());
            }
        }
    }

    private void initButtons() {
        btn_LoadTemplate.setOnAction(actionEvent -> openTemplate());

        btn_ExtractCustomer.setOnAction(actionEvent -> {
            try {
                repair.extractCustomer(customer);
                initCustomerFields();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        btn_ExtractCrane.setOnAction(actionEvent -> {
            System.out.println(customer.nameProperty().get());
        });
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
}
