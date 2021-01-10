package com.example.registration;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class makeCabOrder extends AppCompatActivity implements View.OnClickListener{
    private EditText name;
    private EditText number, pickup;
    private Button makeOrder;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar2;
    private TextView username;

    private DatabaseReference mFirebaseDatabase1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usermakecaborder);

        mAuth = FirebaseAuth.getInstance();//create firebase auth instance
        username= findViewById(R.id.USERNAme);
        name = findViewById(R.id.editTextUserName);
        number = findViewById(R.id.editTextNumber);
        pickup = findViewById(R.id.editTextPickupAddress);


        progressBar2 = findViewById(R.id.ProgressBar);

        makeOrder = findViewById(R.id.sendOrder);
        makeOrder.setOnClickListener(this);//add listener
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url

            String email = user.getEmail();
            username.setText(email);





        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendOrder:
                orderConfirm();//implement method to take inputs, validate them, register user on firebase auth and store user details
                break;

        }

    }

    private void orderConfirm() {
        final String username = name.getText().toString().trim();
        final String Cnumber = number.getText().toString().trim();
        final String location = pickup.getText().toString().trim();

        if(username.isEmpty()){
            name.setError("Please fill the 'user name' field");
            name.requestFocus();
        }

        if(Cnumber.isEmpty()){
            name.setError("please fill the 'contact number' field");
            name.requestFocus();
        }
        if(location.isEmpty()){
            pickup.setError("Please fill the 'where to pick-up' field");
        }
        progressBar2.setVisibility(View.VISIBLE);

        cabBook cab = new cabBook(name,number,pickup);
        //add the project to firebase DB (tools --> Firebase --> RealTime Database
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url

            String email = user.getEmail();

            Toast.makeText(makeCabOrder.this,"HI "+email,Toast.LENGTH_LONG).show();




        }




    }


}

