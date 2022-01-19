package ru.samcold.domain;

import ru.samcold.domain.proxy.CustomerProxy;

public class Customer {
    private String name;
    private String zip;
    private String region;
    private String city;
    private String address;
    private String boss;
    private String post;
    private String phone;

    public Customer() {
    }

    public Customer(String name, String zip, String region, String city, String address, String phone) {
        this.name = name;
        this.zip = zip;
        this.region = region;
        this.city = city;
        this.address = address;
        this.phone = phone;
    }

    public Customer(CustomerProxy proxy) {
        name = proxy.nameProperty().get();
        zip = proxy.zipProperty().get();
        region = proxy.regionProperty().get();
        city = proxy.cityProperty().get();
        address = proxy.addressProperty().get();
        boss = proxy.bossProperty().get();
        post = proxy.postProperty().get();
        phone = proxy.phoneProperty().get();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", zip='" + zip + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
