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
import ru.samcold.domain.proxy.CustomerProxy;
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

    private CustomerProxy customerProxy;
    private ValidationSupport validationSupport;

    @FXML
    void initialize() {
        isLoaded.set(false);
        customerProxy = new CustomerProxy();
        validationSupport = new ValidationSupport();
        initFields();
        initButtons();
    }

    private void initFields() {
        // bind
        txt_CustomerName.textProperty().bindBidirectional(customerProxy.nameProperty());
        txt_CustomerZip.textProperty().bindBidirectional(customerProxy.zipProperty());
        txt_CustomerRegion.textProperty().bindBidirectional(customerProxy.regionProperty());
        txt_CustomerCity.textProperty().bindBidirectional(customerProxy.cityProperty());
        txt_CustomerAddress.textProperty().bindBidirectional(customerProxy.addressProperty());
        txt_CustomerBoss.textProperty().bindBidirectional(customerProxy.bossProperty());
        txt_CustomerPost.textProperty().bindBidirectional(customerProxy.postProperty());
        txt_CustomerPhone.textProperty().bindBidirectional(customerProxy.phoneProperty());

        // validator
        List<Node> list = ((Pane) pane_Customer.getContent()).getChildren();
        setValidation(list);

        // btn_Save ...
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
            customerProxy.fromCustomer(repair.extractCustomer());

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
