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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailsVIewDriver extends AppCompatActivity implements View.OnClickListener {
    private Button deyaislView,logout;
    private TextView name;

    private DatabaseReference databaseReference;
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_v_iew_driver);
        deyaislView = findViewById(R.id.DriverDet);
        logout=findViewById(R.id.Logout);
        name=findViewById(R.id.name);
//        logout.setOnClickListener(this);
        deyaislView.setOnClickListener(this);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url

            String email = user.getEmail();
            name.setText(email);

            Toast.makeText(DetailsVIewDriver.this,"HI "+email,Toast.LENGTH_LONG).show();




        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.Logout:
//                FirebaseAuth.getInstance().signOut();
//
//                Toast.makeText(DetailsVIewDriver.this,"Logged Out  successfully",Toast.LENGTH_LONG).show();
//                startActivity(new Intent(this,MainActivity.class));
//                finish();
            case R.id.DriverDet:
                startActivity(new Intent(this,DriverDetails.class));
                finish();

        }

    }

}







