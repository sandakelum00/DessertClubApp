package com.example.dessert_club_application;

public class Customer {
    String id,name,account,security,expiry;

    public Customer(String id, String name, String account, String security, String expiry) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.security = security;
        this.expiry = expiry;
    }

    public Customer() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }
}