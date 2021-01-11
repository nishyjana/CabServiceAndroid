package com.example.registration;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView SignUp1;
    FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private Button login;
    private EditText editTextEmail,editTextPassword;
    private ProgressBar progressBar2;
    FirebaseAuth fauth;
    FirebaseFirestore fstore;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextPassword= findViewById(R.id.loginTextPassword);
        mAuth = FirebaseAuth.getInstance();
        progressBar2 = findViewById(R.id.ProgressBar);
        editTextEmail = findViewById(R.id.loginTextEmail);//perform binding
        fauth =FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();


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
        try{
            fauth.signInWithEmailAndPassword(emailAddress,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    checkDriver(authResult.getUser().getUid());
                }
            });

        }catch (Exception e){

        }


    }

    private void checkDriver(String uid) {
        DocumentReference df = fstore.collection("Users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG","OnSucess"+documentSnapshot.getData());
                if(documentSnapshot.getBoolean("driver") == true){
                   // startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    Toast.makeText(MainActivity.this,"Hi driver",Toast.LENGTH_LONG).show();

                }
                else if(documentSnapshot.getBoolean("user") == true){
                    startActivity(new Intent(getApplicationContext(),userHomePage.class));


                }
                else if (documentSnapshot.getBoolean("isadmin") == true){
                    startActivity(new Intent(getApplicationContext(),adminHomePage.class));

                }
            }
        });
    }
}