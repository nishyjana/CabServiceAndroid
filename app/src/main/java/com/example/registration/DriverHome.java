package com.example.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DriverHome extends AppCompatActivity {
    private Button driverDet, LogoutBtn;
    private DatabaseReference databaseReference;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home);
        driverDet = findViewById(R.id.driverdet);
        LogoutBtn = findViewById(R.id.lgout);

        databaseReference = FirebaseDatabase.getInstance().getReference();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {


            String email = user.getEmail();


            Toast.makeText(DriverHome.this,"HI Driver"+email,Toast.LENGTH_LONG).show();




        }
        driverDet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    // User is signed in
                   // startActivity(new Intent(DriverHome.this,makeCabOrder.class));

                    String email = user.getEmail();

                    Toast.makeText(DriverHome.this,"HI "+email,Toast.LENGTH_SHORT).show();
                } else {
                    // No user is signed in
                    Toast.makeText(DriverHome.this,"Authentication fatal error. Login again",Toast.LENGTH_SHORT).show();
                }
            }
        });
        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Toast.makeText(DriverHome.this,"Logged Out  successfully",Toast.LENGTH_LONG).show();
                startActivity(new Intent(DriverHome.this,MainActivity.class));
                finish();
            }
        });



    }
}
