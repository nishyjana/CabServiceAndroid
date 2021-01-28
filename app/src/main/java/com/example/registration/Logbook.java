package com.example.registration;

public class Logbook {
    private String customerEmail;
    private  String location;
    private String amount;
    private String driverID;

    public Logbook(String customerEmail, String location, String amount,String driverID) {
        this.customerEmail = customerEmail;
        this.location = location;
        this.amount = amount;
        this.driverID= driverID;
    }

    public Logbook() {
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }
}
