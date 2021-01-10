package com.example.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class makeCabOrder extends AppCompatActivity implements View.OnClickListener{

    private EditText  Pnumber,pickup;
    private Button makeOrder;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar2;
    private TextView username;

    private DatabaseReference mFirebaseDatabase1;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usermakecaborder);

        mAuth = FirebaseAuth.getInstance();//create firebase auth instance
        username= findViewById(R.id.USERNAme);

        Pnumber = findViewById(R.id.editTextNumber);
        pickup = findViewById(R.id.editTextPickupAddress);


        progressBar2 = findViewById(R.id.ProgressBar);

        makeOrder = findViewById(R.id.sendOrder);
        makeOrder.setOnClickListener(this);//add listener


        FirebaseUser Customer = FirebaseAuth.getInstance().getCurrentUser();

        String email = Customer.getEmail();

        username.setText(email);
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

        final String PhoneNumber = Pnumber.getText().toString().trim();
        final String location = pickup.getText().toString().trim();


        if(location.isEmpty()){
            Pnumber.setError("Please fill the Contact number field");
        }

        if(location.isEmpty()){
            pickup.setError("Please fill the 'where to pick-up' field");
        }
        progressBar2.setVisibility(View.VISIBLE);
       try {
           FirebaseUser Customer = FirebaseAuth.getInstance().getCurrentUser();
           String emails = Customer.getEmail();

           DocumentReference newtoRef = db.collection("Bookings").document();
           String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
           cabBook book = new cabBook();
           book.setName(emails);
           book.setNumber(PhoneNumber);
           book.setPickup(location);
           book.setDriver("pending");
           book.setUserID(userID);

           newtoRef.set(book).addOnCompleteListener(new OnCompleteListener<Void>() {
               @Override
               public void onComplete(@NonNull Task<Void> task) {
                   if(task.isSuccessful()){
                       Toast.makeText(makeCabOrder.this,"Booking is  successfull",Toast.LENGTH_SHORT).show();
                       progressBar2.setVisibility(View.GONE);
                       startActivity(new Intent(getApplicationContext(),userHomePage.class));
                   }
                   else {
                       Toast.makeText(makeCabOrder.this,"Booking is  Unsuccessfull",Toast.LENGTH_SHORT).show();
                       progressBar2.setVisibility(View.GONE);
                   }
               }
           });
           }catch (Exception e){
           Toast.makeText(makeCabOrder.this,"Booking is  Unsuccessfull",Toast.LENGTH_SHORT).show();
           progressBar2.setVisibility(View.GONE);
       }
    }
}

