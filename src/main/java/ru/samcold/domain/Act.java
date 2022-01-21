package ru.samcold.domain;

import javafx.beans.property.*;
import java.util.Date;

public class Act {
    private final IntegerProperty rtk = new SimpleIntegerProperty();
    private final StringProperty contractNumber = new SimpleStringProperty();
    private final ObjectProperty<Date> contractDate = new SimpleObjectProperty<>();
    private final StringProperty orderNumber = new SimpleStringProperty();
    private final ObjectProperty<Date> orderDate = new SimpleObjectProperty<>();
    private final StringProperty location = new SimpleStringProperty();
    private final StringProperty period = new SimpleStringProperty();

    public IntegerProperty rtkProperty() {
        return rtk;
    }

    public StringProperty contractNumberProperty() {
        return contractNumber;
    }

    public ObjectProperty<Date> contractDateProperty() {
        return contractDate;
    }

    public StringProperty orderNumberProperty() {
        return orderNumber;
    }

    public ObjectProperty<Date> orderDateProperty() {
        return orderDate;
    }

    public StringProperty locationProperty() {
        return location;
    }

    public StringProperty periodProperty() {
        return period;
    }
}
