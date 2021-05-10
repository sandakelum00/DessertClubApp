package com.example.dessert_club_application;

public class Model {

     String Id;
    String name;
    String account;
    String security;
    String expiry;



    public Model(String Id, String name, String account, String security, String expiry) {
        this.Id = Id;
        this.name = name;
        this.account = account;
        this.security = security;
        this.expiry = expiry;
    }

    public Model() {
    }

    public String getId() {
        return Id;
    }

    public void setCusId(String cusId) {
        this.Id = Id;
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
