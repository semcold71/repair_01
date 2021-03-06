package ru.samcold.controllers;

import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import padeg.lib.Padeg;
import ru.samcold.Main;
import ru.samcold.domain.*;
import ru.samcold.repairing.Extraction;
import ru.samcold.utils.NumberPropertyBinder;
import ru.samcold.utils.StringUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class MainController {

    /**
     * FXML field
     */
    @FXML
    private Button btn_LoadTemplate;
    @FXML
    private Button btn_ExtractCustomer;
    @FXML
    private Button btn_ExtractCrane;
    @FXML
    private Button btn_ExtractRTK;
    @FXML
    private Button btn_Save;
    @FXML
    private Button btn_createConclusion;

    @FXML
    private Button btn_Test;
    @FXML
    private HBox hbox_AnotherButtons;
    //region RTK
    @FXML
    private TitledPane pane_RTK;

    @FXML
    private TextField txt_RTK_Number;

    @FXML
    private TextField txt_RTK_Period;

    @FXML
    private TextField txt_RTK_OrderDate;

    @FXML
    private TextField txt_RTK_OrderNumber;

    @FXML
    private TextField txt_RTK_ContractNumber;

    @FXML
    private TextField txt_RTK_ContractDate;

    @FXML
    private TextField txt_RTK_Location;

    @FXML
    private TextField txt_RTK_Next;
    //endregion
    //region Customer
    @FXML
    private TitledPane pane_Customer;
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
    private TextField txt_CraneIssue;

    @FXML
    private TextField txt_CraneSpan;

    @FXML
    private TextField txt_CraneCapacity;

    @FXML
    private TextField txt_CraneLifting;
    //endregion
    //region Conclusion
    @FXML
    private TitledPane pane_Conclusion;
    @FXML
    private DatePicker dp_Con_SignDate;
    @FXML
    private TextField txt_Con_Tu;
    @FXML
    private TextField txt_Con_ContractNumber;
    @FXML
    private DatePicker dp_Con_ContractDate;
    @FXML
    private TextField txt_Con_OrderNumber;
    @FXML
    private DatePicker dp_Con_OrderDate;
    @FXML
    private DatePicker dp_Con_NextDate;
    @FXML
    private TextField txt_Con_CustomerFull;
    @FXML
    private Button btn_Save_Conclusion;
    //endregion

    private final MyDocument myDocument = MyDocument.getInstance();
    private final Extraction extraction = Extraction.getInstance();
    private final NumberPropertyBinder numberBinder = NumberPropertyBinder.getInstance();
    private final StringUtils stringUtils = StringUtils.getInstance();
    private final BooleanProperty isLoaded = new SimpleBooleanProperty();

    private Rtk rtk;
    private Customer customer;
    private Crane crane;
    private Conclusion conclusion;
    private ValidationSupport validationSupport;
    private ValidationSupport validationSupport1;
    private Validator<String> emptyValidator;

    @FXML
    void initialize() {

        rtk = new Rtk();
        customer = new Customer();
        crane = new Crane();
        conclusion = new Conclusion();

        isLoaded.set(false);
        validationSupport = new ValidationSupport();
        validationSupport1 = new ValidationSupport();
        emptyValidator = Validator.createEmptyValidator("???????????????????? ??????????????????", Severity.ERROR);
        hbox_AnotherButtons.disableProperty().bind(isLoaded.not());

        initRtkFields();
        initCustomerFields();
        initCraneFields();
        initConclusionField();
        initButtons();
    }

    private void loadExternalDocument() {
        String path;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("?????????????? ???????????????? ?????? ????????????????????????????");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Microsoft Word (.docx)", "*.docx"));

        Stage stage = (Stage) btn_ExtractCrane.getScene().getWindow();
        File res = fileChooser.showOpenDialog(stage);

        if (res != null) {
            path = res.getAbsolutePath();
        } else return;

        try {
            myDocument.setExternalDocument(path);
            isLoaded.set(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createOutputDocument() {
        try {
            myDocument.setOutputDocument();

            List<XWPFParagraph> paragraphs = myDocument.getOutputDocument().getParagraphs();

            updateParagraph(paragraphs, "rtk", rtk.numberProperty().get());
            updateParagraph(paragraphs, "craneFull", crane.getFullName());
            updateParagraph(paragraphs, "craneFullRod",
                    stringUtils.firstToLower(stringUtils.strToRod(crane.getFullName())));
            updateParagraph(paragraphs, "contractNumber", rtk.contractNumberProperty().get());
            updateParagraph(paragraphs, "contractDate", rtk.contractDateProperty().get() + " ??.");
            updateParagraph(paragraphs, "customer", customer.nameProperty().get());
            updateParagraph(paragraphs, "orderNumber", rtk.orderNumberProperty().get());

            findInCurrentTable(0, "customerName", customer.nameProperty().get());
            findInCurrentTable(0, "customerZip", customer.zipProperty().get());
            findInCurrentTable(0, "customerRegion", customer.regionProperty().get());
            findInCurrentTable(0, "customerCity", customer.cityProperty().get());
            findInCurrentTable(0, "customerAddress", customer.addressProperty().get());
            findInCurrentTable(0, "customerBoss", customer.bossProperty().get());
            findInCurrentTable(0, "customerPost", customer.postProperty().get());
            findInCurrentTable(0, "customerPhone", customer.phoneProperty().get());

            findInCurrentTable(1, "contractNumber", rtk.contractNumberProperty().get());
            findInCurrentTable(1, "contractDate", rtk.contractDateProperty().get());

            findInCurrentTable(2, "orderNumber", rtk.orderNumberProperty().get());
            findInCurrentTable(2, "orderDate", rtk.orderDateProperty().get());

            findInCurrentTable(3, "location", rtk.locationProperty().get());
            findInCurrentTable(3, "period", rtk.periodProperty().get());

            findInCurrentTable(6, "craneName", crane.nameProperty().get());
            findInCurrentTable(6, "craneMarka", crane.markProperty().get());
            findInCurrentTable(6, "craneMode", crane.modeProperty().get());
            findInCurrentTable(6, "craneZav", crane.zavProperty().get());
            findInCurrentTable(6, "craneReg", crane.regProperty().get());
            findInCurrentTable(6, "craneFactory", crane.factoryProperty().get());
            findInCurrentTable(6, "craneIssue", crane.issueProperty().get());
            findInCurrentTable(6, "craneCapacity", crane.capacityProperty().get());
            findInCurrentTable(6, "craneLifting", crane.liftingProperty().get());
            findInCurrentTable(6, "craneSpan", crane.spanProperty().get());
            findInCurrentTable(28, "orderDate", rtk.orderDateProperty().get());

            XWPFParagraph nextDatePara1 = myDocument.getOutputDocument().getTables().get(23).getRows().get(0).getCell(0).getParagraphs().get(0);
            updateCurrentParagraph(nextDatePara1, "craneFull", crane.getFullName());

            XWPFParagraph nextDatePara2 = myDocument.getOutputDocument().getTables().get(23).getRows().get(1).getCell(1).getParagraphs().get(0);
            updateCurrentParagraph(nextDatePara2, "rtkNext", rtk.nextProperty().get() + " ??.");

            myDocument.getOutputDocument().getTables().get(29).getRow(0).getCell(1).getParagraphs().get(0)
                    .createRun().setText(crane.nameProperty().get());
            myDocument.getOutputDocument().getTables().get(29).getRow(1).getCell(1).getParagraphs().get(0)
                    .createRun().setText(crane.markProperty().get());
            myDocument.getOutputDocument().getTables().get(29).getRow(2).getCell(1).getParagraphs().get(0)
                    .createRun().setText(crane.zavProperty().get());
            myDocument.getOutputDocument().getTables().get(29).getRow(3).getCell(1).getParagraphs().get(0)
                    .createRun().setText(crane.regProperty().get());

            fillHeaderTable(30);
            fillHeaderTable(32);
            fillHeaderTable(33);
            fillHeaderTable(36);

            findInCurrentTable(53, "contractNumber", rtk.contractNumberProperty().get());
            findInCurrentTable(53, "contractDate", rtk.contractDateProperty().get());

            // ???????????? rtk (???????????????????? ?? ???????????? ?????????????? - ???? ??????????????)
            XWPFHeaderFooterPolicy headerFooterPolicy = myDocument.getOutputDocument().getHeaderFooterPolicy();
            XWPFHeader header = headerFooterPolicy.getDefaultHeader();
            XWPFParagraph hParagraph = header.getParagraphs().get(0);
            updateCurrentParagraph(hParagraph, "rtk", rtk.numberProperty().get());

            myDocument.save();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ...
    private void initRtkFields() {

        ChangeListener<String> listener = (((observableValue, s, t1) -> {
            String s1 = rtk.contractNumberProperty().get() == null ? "" : rtk.contractNumberProperty().get();
            String s2 = rtk.numberProperty().get() == null ? "" : rtk.numberProperty().get();
            rtk.orderNumberProperty().set(s1 + "/" + s2);
        }));

        rtk.numberProperty().addListener(listener);
        rtk.contractNumberProperty().addListener(listener);

        // bind
        txt_RTK_Number.textProperty().bindBidirectional(rtk.numberProperty());
        txt_RTK_ContractNumber.textProperty().bindBidirectional(rtk.contractNumberProperty());
        txt_RTK_ContractDate.textProperty().bindBidirectional(rtk.contractDateProperty());
        txt_RTK_OrderNumber.textProperty().bindBidirectional(rtk.orderNumberProperty());
        txt_RTK_OrderDate.textProperty().bindBidirectional(rtk.orderDateProperty());
        txt_RTK_Period.textProperty().bindBidirectional(rtk.periodProperty());
        txt_RTK_Location.textProperty().bindBidirectional(rtk.locationProperty());
        txt_RTK_Next.textProperty().bindBidirectional(rtk.nextProperty());

        // validation
        List<Node> list = ((Pane) pane_RTK.getContent()).getChildren();
        setValidation(list);
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
        List<Node> list = ((Pane) pane_Customer.getContent()).getChildren();
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
        txt_CraneIssue.textProperty().bindBidirectional(crane.issueProperty());
        txt_CraneCapacity.textProperty().bindBidirectional(crane.capacityProperty());
        txt_CraneLifting.textProperty().bindBidirectional(crane.liftingProperty());
        txt_CraneSpan.textProperty().bindBidirectional(crane.spanProperty());

        // validation
        validationSupport.registerValidator(txt_CraneName, emptyValidator);
        validationSupport.registerValidator(txt_CraneFactory, emptyValidator);
        validationSupport.registerValidator(txt_CraneIssue, emptyValidator);
        validationSupport.registerValidator(txt_CraneCapacity, emptyValidator);
        validationSupport.registerValidator(txt_CraneLifting, emptyValidator);
    }

    private void initConclusionField() {
        // bind
        txt_Con_Tu.textProperty().bindBidirectional(conclusion.tuProperty());
        dp_Con_SignDate.valueProperty().bindBidirectional(conclusion.signingDateProperty());
        dp_Con_NextDate.valueProperty().bindBidirectional(conclusion.nextDateProperty());
        txt_Con_ContractNumber.textProperty().bindBidirectional(conclusion.contractNumberProperty());
        dp_Con_ContractDate.valueProperty().bindBidirectional(conclusion.contractDateProperty());
        txt_Con_OrderNumber.textProperty().bindBidirectional(conclusion.orderNumberProperty());
        dp_Con_OrderDate.valueProperty().bindBidirectional(conclusion.orderDateProperty());
        txt_Con_CustomerFull.textProperty().bindBidirectional(conclusion.customerFullProperty());

        // validation
        validationSupport1.registerValidator(txt_Con_Tu, emptyValidator);
//        validationSupport1.registerValidator(dp_Con_SignDate, emptyValidator);
//        validationSupport1.registerValidator(dp_Con_NextDate, emptyValidator);
//        validationSupport1.registerValidator(txt_Con_ContractNumber, emptyValidator);
//        validationSupport1.registerValidator(dp_Con_ContractDate, emptyValidator);
//        validationSupport1.registerValidator(txt_Con_OrderNumber, emptyValidator);
//        validationSupport1.registerValidator(dp_Con_OrderDate, emptyValidator);

        btn_Save_Conclusion.disableProperty().bind(validationSupport1.invalidProperty());
    }

    private void initButtons() {

        btn_LoadTemplate.setOnAction(actionEvent -> loadExternalDocument());

        btn_ExtractRTK.setOnAction(actionEvent -> {
            rtk = extraction.extractRtk();
            initRtkFields();
            pane_RTK.setExpanded(true);
        });

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
            try {
                crane = extraction.extractCrane(this.crane);
                initCraneFields();
                pane_Crane.setExpanded(true);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            pane_Crane.setExpanded(true);
        });

        btn_Save.setOnAction(actionEvent -> {
            createOutputDocument();
        });

        btn_Save.disableProperty().bind(validationSupport.invalidProperty());

        btn_createConclusion.disableProperty().bind(validationSupport.invalidProperty());

        btn_createConclusion.setOnAction(actionEvent -> {
            try {
                myDocument.setConclusionDocument(new XWPFDocument(Main.class.getResourceAsStream("/docx/blank1.docx")));
                conclusion.customerFullProperty().set(customer.getCustomerFull());
                pane_Conclusion.setDisable(false);
                pane_Conclusion.setExpanded(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btn_Save_Conclusion.setOnAction(actionEvent -> {
            try {
                createConclusionDocument();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // ...
        btn_Test.setOnAction(actionEvent -> {
            rtk.numberProperty().set("888");
        });
    }

    private void createConclusionDocument() throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        List<XWPFParagraph> paragraphs = myDocument.getConclusionDocument().getParagraphs();

        updateCurrentParagraph(myDocument.getConclusionDocument().getParagraphs().get(0), "tu", conclusion.tuProperty().get());
        updateCurrentParagraph(myDocument.getConclusionDocument().getParagraphs().get(1), "customerFull", conclusion.customerFullProperty().get());
        updateCurrentParagraph(myDocument.getConclusionDocument().getParagraphs().get(6), "date", formatter.format(conclusion.signingDateProperty().get()) + " ??.");

        updateParagraph(paragraphs, "craneFull", crane.getFullName());
        updateParagraph(paragraphs, "craneFullRod",
                stringUtils.firstToLower(stringUtils.strToRod(crane.getFullName())));
        updateParagraph(paragraphs, "nextDate", formatter.format(conclusion.nextDateProperty().get()) + " ??.");
        updateParagraph(paragraphs, "orderNumber", conclusion.orderNumberProperty().get());

        String contractParaText = "??? " + conclusion.contractNumberProperty().get() + " ???? " + formatter.format(conclusion.contractDateProperty().get()) + " ??.";
        String orderParaText = "??? " + conclusion.orderNumberProperty().get() + " ???? " + formatter.format(conclusion.orderDateProperty().get()) + " ??.";
        findInTables(myDocument.getConclusionDocument(), "contract", contractParaText);
        findInTables(myDocument.getConclusionDocument(), "order", orderParaText);
        findInTables(myDocument.getConclusionDocument(), "craneFullRod", stringUtils.firstToLower(stringUtils.strToRod(crane.getFullName())));
        findInTables(myDocument.getConclusionDocument(), "orderDate", formatter.format(conclusion.orderDateProperty().get()));
        //findInTables(myDocument.getConclusionDocument(), "craneName", crane.nameProperty().get());

        XWPFTable table8 = myDocument.getConclusionDocument().getTables().get(8);
        table8.getRows().get(0).getCell(1).getParagraphs().get(0).createRun().setText(crane.nameProperty().get());
        table8.getRows().get(1).getCell(1).getParagraphs().get(0).createRun().setText(crane.markProperty().get());
        table8.getRows().get(2).getCell(1).getParagraphs().get(0).createRun().setText(crane.modeProperty().get());
        table8.getRows().get(3).getCell(1).getParagraphs().get(0).createRun().setText(crane.zavProperty().get());
        table8.getRows().get(4).getCell(1).getParagraphs().get(0).createRun().setText(crane.regProperty().get());
        table8.getRows().get(5).getCell(1).getParagraphs().get(0).createRun().setText(crane.factoryProperty().get());
        table8.getRows().get(6).getCell(1).getParagraphs().get(0).createRun().setText(crane.issueProperty().get());
        table8.getRows().get(7).getCell(1).getParagraphs().get(0).createRun().setText(crane.capacityProperty().get());
        table8.getRows().get(8).getCell(1).getParagraphs().get(0).createRun().setText(crane.liftingProperty().get());
        table8.getRows().get(9).getCell(1).getParagraphs().get(0).createRun().setText(crane.spanProperty().get());

        XWPFTable table14 = myDocument.getConclusionDocument().getTables().get(14);
        table14.getRows().get(1).getCell(0).getParagraphs().get(0).createRun().setText(crane.nameProperty().get(),0);
        table14.getRows().get(1).getCell(1).getParagraphs().get(0).createRun().setText(crane.markProperty().get(),0);
        table14.getRows().get(1).getCell(2).getParagraphs().get(0).createRun().setText(crane.zavProperty().get(),0);
        table14.getRows().get(1).getCell(3).getParagraphs().get(0).createRun().setText(crane.regProperty().get(),0);

        myDocument.saveConclusion();
    }

    // ...
    private void setValidation(List<Node> nodeList) {
        for (Node node : nodeList) {
            if (node instanceof TextField || node instanceof ComboBox) {
                validationSupport.registerValidator((Control) node, Validator.createEmptyValidator("???????????????????? ??????????????????", Severity.ERROR));
            } else if (node instanceof Pane) {
                setValidation(((Pane) node).getChildren());
            }
        }
    }

    private void updateParagraph(List<XWPFParagraph> paragraphList, String target, String replacement) {
        for (XWPFParagraph paragraph : paragraphList) {
            for (XWPFRun run : paragraph.getRuns()) {
                if (run.getText(0) != null && run.getText(0).equals(target)) {
                    String text = run.getText(0);
                    text = text.replace(text, replacement);
                    run.setText(text, 0);
                }
            }
        }
    }

    private void updateCurrentParagraph(XWPFParagraph para, String target, String replacement) {
        for (XWPFRun run : para.getRuns()) {
            if (run.getText(0) != null && run.getText(0).equals(target)) {
                String text = run.getText(0);
                text = text.replace(text, replacement);
                run.setText(text, 0);
            }
        }
    }

    private void findInTables(XWPFDocument document, String target, String replacement) {
        for (XWPFTable table : document.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    updateParagraph(cell.getParagraphs(), target, replacement);
                }
            }
        }
    }

    private void findInCurrentTable(int tableIndex, String target, String replacement) {
        for (XWPFTableRow row : myDocument.getOutputDocument().getTables().get(tableIndex).getRows()) {
            for (XWPFTableCell cell : row.getTableCells()) {
                updateParagraph(cell.getParagraphs(), target, replacement);
            }
        }
    }

    private void fillHeaderTable(int tableNum) {
        XWPFTable table = myDocument.getOutputDocument().getTables().get(tableNum);
        table.getRows().get(0).getCell(1).getParagraphs().get(0).createRun().setText(crane.nameProperty().get(), 0);
        table.getRows().get(1).getCell(1).getParagraphs().get(0).createRun().setText(crane.markProperty().get(), 0);
        table.getRows().get(2).getCell(1).getParagraphs().get(0).createRun().setText(crane.zavProperty().get(), 0);
        table.getRows().get(3).getCell(1).getParagraphs().get(0).createRun().setText(crane.regProperty().get(), 0);
        table.getRows().get(4).getCell(1).getParagraphs().get(0).createRun().setText(customer.nameProperty().get(), 0);
    }

}
