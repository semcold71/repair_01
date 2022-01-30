package ru.samcold.domain;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Conclusion {

    private final ObjectProperty<Rtk> rtk = new SimpleObjectProperty<>();
    private final StringProperty tu = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> signingDate = new SimpleObjectProperty<>();
    private final StringProperty contractNumber = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> contractDate = new SimpleObjectProperty<>();
    private final StringProperty orderNumber = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> orderDate = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> nextDate = new SimpleObjectProperty<>();

    public Conclusion(Rtk rtk) {
        this.rtk.set(rtk);
    }

    public ObjectProperty<Rtk> rtkProperty() {
        return rtk;
    }

    public StringProperty tuProperty() {
        return tu;
    }

    public ObjectProperty<LocalDate> signingDateProperty() {
        return signingDate;
    }

    public StringProperty contractNumberProperty() {
        return contractNumber;
    }

    public ObjectProperty<LocalDate> contractDateProperty() {
        return contractDate;
    }

    public StringProperty orderNumberProperty() {
        return orderNumber;
    }

    public ObjectProperty<LocalDate> orderDateProperty() {
        return orderDate;
    }

    public ObjectProperty<LocalDate> nextDateProperty() {
        return nextDate;
    }
}
