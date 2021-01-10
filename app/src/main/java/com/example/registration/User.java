package com.example.registration;

public class User {
    private String name;
    private String email;
    private String contact;
    private String password;
    private String address;
    private String type="Customer";


    public User() {
    }



    public User( String email,String name, String contact , String password, String address,String type) {

        this.email = email;
        this.name = name;
        this.contact = contact;
        this.password= password;
        this.address= address;
        this.type = type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
