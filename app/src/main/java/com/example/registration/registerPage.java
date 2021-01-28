package com.example.registration;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class registerPage extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextEmail,editTextUserFullName,editTextContactNumber,editTextAddress,editTextPassword;
    private ProgressBar progressBar2;
     FirebaseAuth mAuth;
     FirebaseFirestore fstore;
     private Switch Driver,Customer;



    private Button registeringButton,registerDriverButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        mAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        //create firebase auth instance

        editTextEmail = findViewById(R.id.editTextEmail);//perform binding
        editTextUserFullName = findViewById(R.id.editTextUserFullName);
        editTextContactNumber = findViewById(R.id.editTextContactNumber);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextPassword= findViewById(R.id.editTextPassword);

        progressBar2 = findViewById(R.id.ProgressBar);
        //registerDriverButton = findViewById(R.id.SignUp3);
       // registerDriverButton.setOnClickListener(this);//add listener
        Customer = findViewById(R.id.customer);
        Customer.setOnClickListener(this) ;
        Driver = findViewById(R.id.driver);
        Driver.setOnClickListener(this) ;


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.driver:
               if (Driver.isChecked()){
                   registerDrivers();
               }



            case R.id.customer:
                if (Customer.isChecked()){
                    registerUser();
                }

                break;

        }

    }
    public void registerDrivers(){
        //retrieve inputs
        final String emailAddress = editTextEmail.getText().toString().trim();
        final String fullName = editTextUserFullName.getText().toString().trim();
        final String contact = editTextContactNumber.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();


        final String address = editTextAddress.getText().toString().trim();

        //perform validation
        if (emailAddress.isEmpty()) {
            editTextEmail.setError("Email Address field cannot be left empty");
            editTextEmail.requestFocus();
            return;
        }
        if (fullName.isEmpty()) {
            editTextUserFullName.setError("Name field cannot be left empty");
            editTextUserFullName.requestFocus();
            return;

        }
        if (contact.isEmpty()) {
            editTextContactNumber.setError("Contact Number field cannot be left empty");
            editTextContactNumber.requestFocus();
            return;
        }
        if(address.isEmpty()) {
            editTextAddress.setError("Address field cannot be left empty");
            editTextAddress.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            editTextEmail.setError("Enter a valid Email Address");
            editTextEmail.requestFocus();
            return;
        }
        if(password.length()<6){
            editTextPassword.setError("Password should not exceed more than 6 characters");
            editTextPassword.requestFocus();
            return;
        }
        progressBar2.setVisibility(View.VISIBLE);


        mAuth.createUserWithEmailAndPassword(emailAddress,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser user = mAuth.getCurrentUser();
                Toast.makeText(registerPage.this,"Account Created",Toast.LENGTH_SHORT).show();
                DocumentReference df = fstore.collection("Users").document(user.getUid());

                User userDet = new User(fullName,emailAddress,contact,password,address,false,true,false,"Active");

                df.set(userDet).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(registerPage.this,"Account details stored",Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(registerPage.this,"Account details Fialure",Toast.LENGTH_SHORT).show();
                    }
                });

                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(registerPage.this,"Account Creation Failure ",Toast.LENGTH_LONG).show();

            }
        });

    }
    public void registerUser() {
        final String emailAddress = editTextEmail.getText().toString().trim();
        final String fullName = editTextUserFullName.getText().toString().trim();
        final String contact = editTextContactNumber.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();


        final String address = editTextAddress.getText().toString().trim();

        //perform validation
        if (emailAddress.isEmpty()) {
            editTextEmail.setError("Email Address field cannot be left empty");
            editTextEmail.requestFocus();
            return;
        }
        if (fullName.isEmpty()) {
            editTextUserFullName.setError("Name field cannot be left empty");
            editTextUserFullName.requestFocus();
            return;

        }
        if (contact.isEmpty()) {
            editTextContactNumber.setError("Contact Number field cannot be left empty");
            editTextContactNumber.requestFocus();
            return;
        }
        if(address.isEmpty()) {
            editTextAddress.setError("Address field cannot be left empty");
            editTextAddress.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            editTextEmail.setError("Enter a valid Email Address");
            editTextEmail.requestFocus();
            return;
        }
        if(password.length()<6){
            editTextPassword.setError("Password should not exceed more than 6 characters");
            editTextPassword.requestFocus();
            return;
        }
        progressBar2.setVisibility(View.VISIBLE);


        mAuth.createUserWithEmailAndPassword(emailAddress,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser user = mAuth.getCurrentUser();
                Toast.makeText(registerPage.this,"Account Created",Toast.LENGTH_SHORT).show();
                DocumentReference df = fstore.collection("Users").document(user.getUid());
                User userDet = new User(fullName,emailAddress,contact,password,address,false,false,true,"pending");
                df.set(userDet).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(registerPage.this,"Account details stored",Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(registerPage.this,"Account details Fialure",Toast.LENGTH_SHORT).show();
                    }
                });

                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(registerPage.this,""+e,Toast.LENGTH_LONG).show();

            }
        });


    }
    }



