package com.example.registration;

public class cabBook {
    private String name,number, pickup,driver,userID;



    public cabBook(String name, String number, String pickup, String driver,String userID) {
        this.name = name;
        this.number = number;
        this.pickup = pickup;
        this.driver = driver;
        this.userID=userID;

    }

    public cabBook(String name, String pickup) {
        this.name = name;
        this.pickup = pickup;
    }

    public cabBook() {
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }
}
