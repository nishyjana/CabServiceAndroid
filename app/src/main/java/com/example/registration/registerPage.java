package com.example.registration;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class registerPage extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextEmail,editTextUserFullName,editTextContactNumber,editTextAddress,editTextPassword;
    private ProgressBar progressBar2;
    private FirebaseAuth mAuth;
    private Button registeringButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        mAuth = FirebaseAuth.getInstance();//create firebase auth instance

        editTextEmail = findViewById(R.id.editTextEmail);//perform binding
        editTextUserFullName = findViewById(R.id.editTextUserFullName);
        editTextContactNumber = findViewById(R.id.editTextContactNumber);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextPassword= findViewById(R.id.editTextPassword);

        progressBar2 = findViewById(R.id.ProgressBar);

        registeringButton = findViewById(R.id.SignUp2);
        registeringButton.setOnClickListener(this);//add listener
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.SignUp2:
                registerUser();

                //implement method to take inputs, validate them, register user on firebase auth and store user details
                break;

        }

    }
    public void registerUser() {
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


            mAuth.createUserWithEmailAndPassword(emailAddress,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){ //if the user created successfully in firebase auth
                        User user = new User(emailAddress,fullName,contact,password,address,"Customer");
                        //add the project to firebase DB (tools --> Firebase --> RealTime Database
                        FirebaseDatabase.getInstance().getReferenceFromUrl("https://cab-service-app-default-rtdb.firebaseio.com/").child(FirebaseAuth.getInstance().
                               getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){//if added to DB
                                    Toast.makeText(registerPage.this,"User registered successfully",Toast.LENGTH_LONG).show();

                                progressBar2.setVisibility(View.GONE);
                                    startActivity(new Intent(getApplicationContext(),MainActivity.class));

                            }
                            else {//user not added to the DB
                                    Toast.makeText(registerPage.this, "User not registered successfully", Toast.LENGTH_LONG).show();
                                    progressBar2.setVisibility(View.GONE);

                                }
                            }
                        });
                    }
                    else {
                        Toast.makeText(registerPage.this, "User has already registered", Toast.LENGTH_LONG).show();
                        progressBar2.setVisibility(View.GONE);
                    }
                }
            });
        }
    }



