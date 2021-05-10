package com.example.dessert_club_application;

public class Place {
    String id, province, street, city, phone;

    public Place(String id, String province, String street, String city, String phone) {
        this.id = id;
        this.province = province;
        this.street = street;
        this.city = city;
        this.phone = phone;

    }

    public Place() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}