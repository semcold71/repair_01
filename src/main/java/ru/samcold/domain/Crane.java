package ru.samcold.domain;

import javafx.beans.property.*;

public class Crane {
    private final StringProperty name = new SimpleStringProperty();     // наименование, тип
    private final StringProperty mark = new SimpleStringProperty();     // марка
    private final StringProperty mode = new SimpleStringProperty();     // исполнение
    private final StringProperty zav = new SimpleStringProperty();      // зав.№
    private final StringProperty reg = new SimpleStringProperty();      // рег.№
    private final StringProperty factory = new SimpleStringProperty();  // завод-изготовитель
    private final StringProperty issue = new SimpleStringProperty();  // год выпуска
    private final StringProperty capacity = new SimpleStringProperty(); // грузоподъемность
    private final StringProperty lifting = new SimpleStringProperty();  // высота подъема
    private final StringProperty span = new SimpleStringProperty();     // пролет (вылет)

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

    @Override
    public String toString() {
        return "Crane{" +
                "name=" + name +
                ", mark=" + mark +
                ", mode=" + mode +
                ", zav=" + zav +
                ", reg=" + reg +
                ", factory=" + factory +
                ", issue=" + issue +
                ", capacity=" + capacity +
                ", lifting=" + lifting +
                ", span=" + span +
                '}';
    }

    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        sb
                .append(name.get())
                .append(mark.get() != null ? " " + mark.get() : "")
                .append(zav.get() != null ? ", зав.№ " + zav.get() : "")
                .append(reg.get() != null ? ", рег.№ " + reg.get() : "");

        return sb.toString();
    }
}
