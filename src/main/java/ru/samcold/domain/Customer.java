package ru.samcold.domain;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Customer {
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty zip = new SimpleStringProperty();
    private final StringProperty region = new SimpleStringProperty();
    private final StringProperty city = new SimpleStringProperty();
    private final StringProperty address = new SimpleStringProperty();
    private final StringProperty boss = new SimpleStringProperty();
    private final StringProperty post = new SimpleStringProperty();
    private final StringProperty phone = new SimpleStringProperty();

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty zipProperty() {
        return zip;
    }

    public StringProperty regionProperty() {
        return region;
    }

    public StringProperty cityProperty() {
        return city;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public StringProperty bossProperty() {
        return boss;
    }

    public StringProperty postProperty() {
        return post;
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    // ...
    public String getCustomerFull() {
        return name.get() + ", " + zip.get() + ", " + region.get() + ", " + city.get() + ", " + address.get();
    }

    public void clear() {
        nameProperty().set("");
        zipProperty().set("");
        regionProperty().set("");
        cityProperty().set("");
        addressProperty().set("");
        bossProperty().set("");
        postProperty().set("");
        phoneProperty().set("");
    }

}
