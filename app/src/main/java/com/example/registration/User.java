package com.example.registration;

public class User {
    private String name;
    private String email;
    private String contact;
    private String password;
    private String address;
    private Boolean isadmin;
    private Boolean isDriver;
    private Boolean isUser;
    private String status;


    public User() {
    }


    public User(String name, String email, String contact, String password, String address, Boolean isadmin, Boolean isDriver, Boolean isUser,String status) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.password = password;
        this.address = address;
        this.isadmin = isadmin;
        this.isDriver = isDriver;
        this.isUser = isUser;
        this.status =status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(Boolean isadmin) {
        this.isadmin = isadmin;
    }

    public Boolean getDriver() {
        return isDriver;
    }

    public void setDriver(Boolean driver) {
        isDriver = driver;
    }

    public Boolean getUser() {
        return isUser;
    }

    public void setUser(Boolean user) {
        isUser = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
