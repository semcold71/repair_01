package ru.samcold.domain.proxy;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import ru.samcold.domain.Customer;

public class CustomerProxy {
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

    public void fromCustomer (Customer customer) {
        name.set(customer.getName());
        zip.set(customer.getZip());
        region.set(customer.getRegion());
        city.set(customer.getCity());
        address.set(customer.getAddress());
        boss.set(customer.getBoss());
        post.set(customer.getPost());
        phone.set(customer.getPhone());
    }
}
