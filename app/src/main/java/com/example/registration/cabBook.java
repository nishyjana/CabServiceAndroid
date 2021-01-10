package com.example.registration;

import android.widget.EditText;
import android.widget.TextView;

public class cabBook {
    private String name,number, pickup;

    public cabBook(TextView name, EditText number, EditText pickup) {
    }

    public cabBook(String name, String number, String pickup) {
        this.name = name;
        this.number = number;
        this.pickup = pickup;
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
