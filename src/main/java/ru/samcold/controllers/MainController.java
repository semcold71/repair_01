package ru.samcold.controllers;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import ru.samcold.domain.Act;
import ru.samcold.domain.Crane;
import ru.samcold.domain.MyDocument;
import ru.samcold.domain.Customer;
import ru.samcold.repairing.Extraction;
import ru.samcold.utils.NumberPropertyBinder;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainController {

    /**
     * FXML field
     */
    //region Customer
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
    //region Crane
    @FXML
    private TitledPane pane_Crane;

    @FXML
    private TextField txt_CraneName;

    @FXML
    private TextField txt_CraneMark;

    @FXML
    private TextField txt_CraneMode;

    @FXML
    private TextField txt_CraneReg;

    @FXML
    private TextField txt_CraneZav;

    @FXML
    private TextField txt_CraneFactory;

    @FXML
    private ComboBox<Integer> cbo_CraneIssue;

    @FXML
    private TextField txt_CraneSpan;

    @FXML
    private TextField txt_CraneCapacity;

    @FXML
    private TextField txt_CraneLifting;
    //endregion

    private final ObservableList<Integer> years = FXCollections.observableArrayList(
            IntStream.rangeClosed(1950, LocalDate.now().getYear()).boxed().collect(Collectors.toList()));

    private final MyDocument myDocument = MyDocument.getInstance();
    private final Extraction extraction = Extraction.getInstance();
    private final NumberPropertyBinder numberBinder = NumberPropertyBinder.getInstance();
    private final BooleanProperty isLoaded = new SimpleBooleanProperty();

    private Act act;
    private Customer customer;
    private Crane crane;
    private ValidationSupport validationSupport;

    @FXML
    void initialize() {

        act = new Act();
        customer = new Customer();
        crane = new Crane();

        validationSupport = new ValidationSupport();
        isLoaded.set(false);
        btn_ExtractCustomer.disableProperty().bind(isLoaded.not());

        cbo_CraneIssue.setItems(years);

        initCustomerFields();
        initCraneFields();
        initButtons();
    }

    private void openTemplate() {
        String path;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выбрать документ для редактирования");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Microsoft Word (.docx)", "*.docx"));

        Stage stage = (Stage) btn_ExtractCrane.getScene().getWindow();
        File res = fileChooser.showOpenDialog(stage);

        if (res != null) {
            path = res.getAbsolutePath();
        } else return;

        try {
            myDocument.setTemplate(path);
            isLoaded.set(true);
            customer.clear();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initCustomerFields() {
        txt_CustomerName.textProperty().bindBidirectional(customer.nameProperty());
        txt_CustomerZip.textProperty().bindBidirectional(customer.zipProperty());
        txt_CustomerRegion.textProperty().bindBidirectional(customer.regionProperty());
        txt_CustomerCity.textProperty().bindBidirectional(customer.cityProperty());
        txt_CustomerAddress.textProperty().bindBidirectional(customer.addressProperty());
        txt_CustomerBoss.textProperty().bindBidirectional(customer.bossProperty());
        txt_CustomerPost.textProperty().bindBidirectional(customer.postProperty());
        txt_CustomerPhone.textProperty().bindBidirectional(customer.phoneProperty());

        // validator
        List<Node> list = ((Pane) pane_Crane.getContent()).getChildren();
        setValidation(list);
    }

    private void initCraneFields() {
        // bind
        txt_CraneName.textProperty().bindBidirectional(crane.nameProperty());
        txt_CraneMark.textProperty().bindBidirectional(crane.markProperty());
        txt_CraneMode.textProperty().bindBidirectional(crane.modeProperty());
        txt_CraneZav.textProperty().bindBidirectional(crane.zavProperty());
        txt_CraneReg.textProperty().bindBidirectional(crane.regProperty());
        txt_CraneFactory.textProperty().bindBidirectional(crane.factoryProperty());
        cbo_CraneIssue.valueProperty().bindBidirectional(crane.issueProperty());
        numberBinder.bind(txt_CraneCapacity, crane.capacityProperty());
        numberBinder.bind(txt_CraneLifting, crane.liftingProperty());
        numberBinder.bind(txt_CraneSpan, crane.spanProperty());

        // validation
        List<Node> list = ((Pane) pane_Customer.getContent()).getChildren();
        setValidation(list);
    }

    private void setValidation(List<Node> nodeList) {
        for (Node node : nodeList) {
            if (node instanceof TextField || node instanceof ComboBox) {
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
                customer = extraction.extractCustomer(customer);
                initCustomerFields();
                pane_Customer.setExpanded(true);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        btn_ExtractCrane.setOnAction(actionEvent -> {

            act = extraction.extractAct();
            System.out.println(act);

            // test
            crane.issueProperty().set(1960);
            crane.capacityProperty().set(666.66);
            crane.liftingProperty().set(123.33);
            crane.spanProperty().set(324.45);

            pane_Crane.setExpanded(true);
        });
    }

}
