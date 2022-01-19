package ru.samcold.domain;

import javafx.beans.property.*;

public class CraneProxy {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty mark = new SimpleStringProperty();
    private final StringProperty mode = new SimpleStringProperty();
    private final StringProperty zav = new SimpleStringProperty();
    private final StringProperty reg = new SimpleStringProperty();
    private final StringProperty factory = new SimpleStringProperty();
    private final IntegerProperty issue = new SimpleIntegerProperty();
    private final DoubleProperty capacity = new SimpleDoubleProperty();
    private final DoubleProperty lifting = new SimpleDoubleProperty();
    private final DoubleProperty span = new SimpleDoubleProperty();
    private final DoubleProperty track = new SimpleDoubleProperty();

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

    public IntegerProperty issueProperty() {
        return issue;
    }

    public DoubleProperty capacityProperty() {
        return capacity;
    }

    public DoubleProperty liftingProperty() {
        return lifting;
    }

    public DoubleProperty spanProperty() {
        return span;
    }

    public DoubleProperty trackProperty() {
        return track;
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
        track.set(crane.getTrack());
    }
}
