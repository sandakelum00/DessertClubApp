package com.example.dessert_club_application;

public class PuddingModel {
    private String key;
    private String pType;
    private String price;
    private String qty;

    public PuddingModel() {
    }

    public PuddingModel(String pType, String price, String qty) {
        this.pType = pType;
        this.price = price;
        this.qty = qty;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "PuddingModel{" +
                "key='" + key + '\'' +
                ", pType='" + pType + '\'' +
                ", price='" + price + '\'' +
                ", qty='" + qty + '\'' +
                '}';
    }
}
