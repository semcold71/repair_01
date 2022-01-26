package ru.samcold.domain;

import javafx.beans.property.*;

public class Rtk {
    private final StringProperty number = new SimpleStringProperty();
    private final StringProperty contractNumber = new SimpleStringProperty();
    private final StringProperty contractDate = new SimpleStringProperty();
    private final StringProperty orderNumber = new SimpleStringProperty();
    private final StringProperty orderDate = new SimpleStringProperty();
    private final StringProperty period = new SimpleStringProperty();
    private final StringProperty location = new SimpleStringProperty();
    private final StringProperty next = new SimpleStringProperty();

    public StringProperty numberProperty() {
        return number;
    }

    public StringProperty contractNumberProperty() {
        return contractNumber;
    }

    public StringProperty contractDateProperty() {
        return contractDate;
    }

    public StringProperty orderNumberProperty() {
        return orderNumber;
    }

    public StringProperty orderDateProperty() {
        return orderDate;
    }

    public StringProperty periodProperty() {
        return period;
    }

    public StringProperty locationProperty() {
        return location;
    }

    public StringProperty nextProperty() {
        return next;
    }

    @Override
    public String toString() {
        return "Rtk{" +
                "number=" + number +
                ", contractNumber=" + contractNumber +
                ", contractDate=" + contractDate +
                ", orderNumber=" + orderNumber +
                ", orderDate=" + orderDate +
                ", period=" + period +
                ", location=" + location +
                ", next=" + next +
                '}';
    }
}
