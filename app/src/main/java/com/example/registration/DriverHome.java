package com.example.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.firestore.paging.FirestorePagingAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DriverHome extends AppCompatActivity  {
    private Button driverDet, LogoutBtn, ViewBook;
    private DatabaseReference databaseReference;
    FirebaseAuth mAuth;
    private Switch status;
    private FirestorePagingAdapter adapter;
    private FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home);
        driverDet = findViewById(R.id.driverdet);
        LogoutBtn = findViewById(R.id.lg);
        ViewBook = findViewById(R.id.books);
        firebaseFirestore = FirebaseFirestore.getInstance();
        status = findViewById(R.id.statusdriver);
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(status.isChecked()){
                   try {
                       FirebaseUser user = mAuth.getCurrentUser();
                       //Toast.makeText(registerPage.this,"Account Created",Toast.LENGTH_SHORT).show();
                       DocumentReference docRef = firebaseFirestore.collection("Users").document("g706GSQdkpPw1o4VUReTBBOAFIX2");

                       docRef
                               .update("status", "Active")
                               .addOnSuccessListener(new OnSuccessListener<Void>() {
                                   @Override
                                   public void onSuccess(Void aVoid) {
                                       Toast.makeText(DriverHome.this, " This is sucess  ", Toast.LENGTH_SHORT).show();
                                   }
                               })
                               .addOnFailureListener(new OnFailureListener() {
                                   @Override
                                   public void onFailure(@NonNull Exception e) {

                                   }
                               });
                   }catch (Exception e){
                       Toast.makeText(DriverHome.this, " "+e, Toast.LENGTH_SHORT).show();

                   }

                }
            }
        }) ;


        databaseReference = FirebaseDatabase.getInstance().getReference();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {


            String email = user.getEmail();


            Toast.makeText(DriverHome.this,"HI Driver"+email,Toast.LENGTH_LONG).show();




        }
        ViewBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DriverHome.this,DriverBookingView.class));
            }
        });
        driverDet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    // User is signed in
                   startActivity(new Intent(DriverHome.this,DriverDetails.class));

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

    private void updateStatus() {

        Toast.makeText(DriverHome.this,"updated",Toast.LENGTH_SHORT).show();
    }


}
