package ru.samcold.domain;

import javafx.beans.property.*;
import java.util.Date;

public class Act {
    private final StringProperty rtk = new SimpleStringProperty();
    private final StringProperty contractNumber = new SimpleStringProperty();
    private final StringProperty contractDate = new SimpleStringProperty();
    private final StringProperty orderNumber = new SimpleStringProperty();
    private final StringProperty orderDate = new SimpleStringProperty();
    private final StringProperty location = new SimpleStringProperty();
    private final StringProperty period = new SimpleStringProperty();

    public StringProperty rtkProperty() {
        return rtk;
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

    public StringProperty locationProperty() {
        return location;
    }

    public StringProperty periodProperty() {
        return period;
    }

    @Override
    public String toString() {
        return "Act{" +
                "rtk=" + rtk +
                ", contractNumber=" + contractNumber +
                ", contractDate=" + contractDate +
                ", orderNumber=" + orderNumber +
                ", orderDate=" + orderDate +
                ", location=" + location +
                ", period=" + period +
                '}';
    }
}
