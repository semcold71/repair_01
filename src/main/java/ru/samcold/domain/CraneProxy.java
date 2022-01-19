package ru.samcold.domain;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CraneProxy {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty mark = new SimpleStringProperty();
    private final StringProperty mode = new SimpleStringProperty();
    private final StringProperty zav = new SimpleStringProperty();
    private final StringProperty reg = new SimpleStringProperty();
    private final StringProperty factory = new SimpleStringProperty();
    private final StringProperty issue = new SimpleStringProperty();
    private final StringProperty capacity = new SimpleStringProperty();
    private final StringProperty lifting = new SimpleStringProperty();
    private final StringProperty span = new SimpleStringProperty();

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty markProperty() {
        return mark;
    }

    public StringProperty modeProperty() {
        return mode;
    }

    public StringProperty zavProperty() {
        return zav;
    }

    public StringProperty regProperty() {
        return reg;
    }

    public StringProperty factoryProperty() {
        return factory;
    }

    public StringProperty issueProperty() {
        return issue;
    }

    public StringProperty capacityProperty() {
        return capacity;
    }

    public StringProperty liftingProperty() {
        return lifting;
    }

    public StringProperty spanProperty() {
        return span;
    }

    public void fromCrane(Crane crane) {
        name.set(crane.getName());
        mark.set(crane.getMark());
        mode.set(crane.getMode());
        zav.set(crane.getZav());
        reg.set(crane.getReg());
        factory.set(crane.getFactory());
        issue.set(crane.getIssue());
        capacity.set(crane.getCapacity());
        lifting.set(crane.getLifting());
        span.set(crane.getSpan());
    }
}
