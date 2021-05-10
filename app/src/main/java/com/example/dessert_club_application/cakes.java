package com.example.dessert_club_application;

public class cakes {

    String otherF,otherT,flavor,topping;

    public cakes(){
    }

    public cakes(String otherF, String otherT, String flavor, String topping) {
        this.otherF = otherF;
        this.otherT = otherT;
        this.flavor = flavor;
        this.topping = topping;
    }

    public String getOtherF() {
        return otherF;
    }

    public void setOtherF(String otherF) {
        this.otherF = otherF;
    }

    public String getOtherT() {
        return otherT;
    }

    public void setOtherT(String otherT) {
        this.otherT = otherT;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getTopping() {
        return topping;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }
}
