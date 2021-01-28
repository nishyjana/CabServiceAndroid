package com.example.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class adminHomePage extends AppCompatActivity implements View.OnClickListener {

    private TextView ProfileName;
    private Button logout, DriverDetaisl,logbook;
    FirebaseFirestore fstore;
    FirebaseAuth fauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        ProfileName =findViewById(R.id.text);
        logout = findViewById(R.id.Logout);
        logbook = findViewById(R.id.LogBook);
        DriverDetaisl = findViewById(R.id.DriverDet);
        logout.setOnClickListener(this);
        DriverDetaisl.setOnClickListener(this);
        logbook.setOnClickListener(this);
        fauth = FirebaseAuth.getInstance();
        fstore= FirebaseFirestore.getInstance();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
       try {
           if (user != null) {
               Toast.makeText(adminHomePage.this,"Logged in as admin ",Toast.LENGTH_SHORT).show();
           }

           else{
               Toast.makeText(adminHomePage.this,"Login Fail ",Toast.LENGTH_SHORT).show();
           }
       }
       catch ( Exception e){

           Toast.makeText(adminHomePage.this,"Login Fail "+e,Toast.LENGTH_SHORT).show();
       }
        }


    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.DriverDet:
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if (user != null) {
                        // User is signed in
                        startActivity(new Intent(this, AdminBookingView.class));

                    } else {
                        // No user is signed in
                        Toast.makeText(adminHomePage.this, "Authentication fata error. Login again", Toast.LENGTH_SHORT).show();
                    }

                    break;
                case R.id.LogBook:
                    //Toast.makeText(adminHomePage.this, "hi logbook", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(this, ADminlogbookView.class));
                    break;


                case R.id.Logout:
                    FirebaseAuth.getInstance().signOut();

                    Toast.makeText(adminHomePage.this, "Logged Out  successfully", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(this, MainActivity.class));
                    finish();


            }


        } catch (Exception e) {
            Toast.makeText(adminHomePage.this, "UnExpected Error. Logout and Login again", Toast.LENGTH_LONG).show();

        }
    }


}