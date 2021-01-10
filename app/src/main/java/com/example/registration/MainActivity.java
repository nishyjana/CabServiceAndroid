package com.example.registration;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView SignUp1;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private Button login;
    private EditText editTextEmail,editTextPassword;
    private ProgressBar progressBar2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextPassword= findViewById(R.id.loginTextPassword);
        mAuth = FirebaseAuth.getInstance();
        progressBar2 = findViewById(R.id.ProgressBar);
        editTextEmail = findViewById(R.id.loginTextEmail);//perform binding

        SignUp1 = findViewById(R.id.SignUp);
        SignUp1.setOnClickListener(this);

        login = findViewById(R.id.Login);
        login.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.SignUp:
                startActivity(new Intent(this,registerPage.class));
                break;
            case  R.id.Login:
                login();


                break;
        }

    }
    public void login(){
        final String emailAddress = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();

        if(!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            editTextEmail.setError("Enter a valid Email Address");
            editTextEmail.requestFocus();

            return;
        }
        progressBar2.setVisibility(View.VISIBLE);
        try {
            mAuth.signInWithEmailAndPassword(emailAddress,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                   try {
                       if(task.isSuccessful()){
                           FirebaseUser user = mAuth.getCurrentUser();

                           Toast.makeText(MainActivity.this,"Logged in successfully",Toast.LENGTH_SHORT).show();

                           progressBar2.setVisibility(View.GONE);
                           startActivity(new Intent(getApplicationContext(),userHomePage.class));



                       }
                       else{
                           Toast.makeText(MainActivity.this," LogIn Failed",Toast.LENGTH_LONG).show();
                           progressBar2.setVisibility(View.GONE);

                       }
                   }
                   catch (Exception e){
                       Toast.makeText(MainActivity.this,"SomeThing Wrong in LogIn",Toast.LENGTH_LONG).show();

                   }

                }
            });


        }catch (Exception e){

        }

    }
}