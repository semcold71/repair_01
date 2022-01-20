package ru.samcold.domain;

import javafx.beans.property.*;

public class Crane {
    private final StringProperty name = new SimpleStringProperty();     // наименование, тип
    private final StringProperty mark = new SimpleStringProperty();     // марка
    private final StringProperty mode = new SimpleStringProperty();     // исполнение
    private final StringProperty zav = new SimpleStringProperty();      // зав.№
    private final StringProperty reg = new SimpleStringProperty();      // рег.№
    private final StringProperty factory = new SimpleStringProperty();  // завод-изготовитель

    // чтобы привязывался combobox надо делать ObjectProperty<Integer>, а не IntegerProperty
    private final ObjectProperty<Integer> issue = new SimpleObjectProperty<>();  // год выпуска

    private final DoubleProperty capacity = new SimpleDoubleProperty(); // грузоподъемность
    private final DoubleProperty lifting = new SimpleDoubleProperty();  // высота подъема
    private final DoubleProperty span = new SimpleDoubleProperty();     // пролет (вылет)
    private final DoubleProperty track = new SimpleDoubleProperty();    // колея

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

    public ObjectProperty<Integer> issueProperty() {
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
}
