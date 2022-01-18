package ru.samcold.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ru.samcold.domain.Customer;
import ru.samcold.domain.MyDocument;
import ru.samcold.repairing.Repair;

import java.io.IOException;

public class MainController {

    @FXML
    private Button btnTableRepair;

    private final MyDocument myDocument = MyDocument.getInstance();
    private final Repair repair = Repair.getInstance();

    private Customer customer;

    @FXML
    void initialize() {

        btnTableRepair.setOnAction(actionEvent -> {
            try {
                myDocument.setTemplate();
                customer = repair.extractCustomer();

            } catch (IOException | IllegalAccessException e) {
                e.printStackTrace();
            }

            System.out.println(customer);
        });
    }

}
