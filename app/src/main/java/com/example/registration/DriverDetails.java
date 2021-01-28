package com.example.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class DriverDetails extends AppCompatActivity  implements View.OnClickListener{
    private EditText Customer, location, amount;
    private Button updatelog;
    private DatabaseReference mFirebaseDatabase1;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_details);
        updatelog = findViewById(R.id.updateLog);
        Customer = findViewById(R.id.customer);
        location = findViewById(R.id.location);
        amount = findViewById(R.id.amount);
        updatelog.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.updateLog:
                updatelogbook();
        }


    }

    private void updatelogbook() {
        final String custEmail = Customer.getText().toString().trim();
        final String locat = location.getText().toString().trim();
        final String amt = amount.getText().toString().trim();
        try {
            FirebaseUser Customer = FirebaseAuth.getInstance().getCurrentUser();
            String emails = Customer.getEmail();

            DocumentReference newtoRef = db.collection("Logbook").document();
            String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
            Logbook book = new Logbook();
            book.setCustomerEmail(custEmail);
            book.setLocation(locat);
            book.setAmount(amt);
            book.setDriverID(emails);

            newtoRef.set(book).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(DriverDetails.this,"Sucess",Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(getApplicationContext(),DriverHome.class));
                    }
                    else {
                        Toast.makeText(DriverDetails.this,"Failure",Toast.LENGTH_SHORT).show();


                    }
                }
            });

        }
        catch (Exception e) {
            Toast.makeText(DriverDetails.this," "+e,Toast.LENGTH_SHORT).show();
        }
        }



}