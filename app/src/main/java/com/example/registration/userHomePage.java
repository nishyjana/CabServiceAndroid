package com.example.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class userHomePage extends AppCompatActivity implements View.OnClickListener {
    private Button bookCab,Logout;
    private DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    private ProgressBar progressBar2;
    private TextView username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainuserhomepage);
        mAuth = FirebaseAuth.getInstance();
        progressBar2 = findViewById(R.id.ProgressBar);

        bookCab = findViewById(R.id.orderCab);
        bookCab.setOnClickListener(this);
        Logout = findViewById(R.id.Logout);
        username = findViewById(R.id.USERNAme);
        Logout.setOnClickListener(this);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url

            String email = user.getEmail();
            username.setText("Welcome           "+ email);

            Toast.makeText(userHomePage.this,"HI "+email,Toast.LENGTH_LONG).show();




        }



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.orderCab:
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    // User is signed in
                    startActivity(new Intent(this,makeCabOrder.class));

                    String email = user.getEmail();

                    Toast.makeText(userHomePage.this,"HI "+email,Toast.LENGTH_SHORT).show();
                } else {
                    // No user is signed in
                    Toast.makeText(userHomePage.this,"Authentication fata error. Login again",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.Logout:
                FirebaseAuth.getInstance().signOut();

                Toast.makeText(userHomePage.this,"Logged Out  successfully",Toast.LENGTH_LONG).show();
                startActivity(new Intent(this,MainActivity.class));
                finish();



        }


    }}


