package com.example.utpal_tank_final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registration extends AppCompatActivity {


    private EditText   UserPassword, UserEmail;
    private Button regButton;
    private TextView UserLogin;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        UserPassword = (EditText) findViewById(R.id.etUserPassword);
        UserEmail = (EditText) findViewById(R.id.etUserEmail);
        UserLogin = (TextView) findViewById(R.id.tvinfo);
        regButton = (Button) findViewById(R.id.btnRegister);


        firebaseAuth = FirebaseAuth.getInstance().getInstance();


        UserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(registration.this,MainActivity.class);
                startActivity(intent);

            }
        });
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()) {

                    String User_email = UserEmail.getText().toString().trim();
                    String User_password = UserPassword.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(User_email,User_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()) {
                                Toast.makeText(registration.this, "registration is done", Toast.LENGTH_SHORT).show();
                                Intent intent =  new Intent(registration.this,MainActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(registration.this, "registration failed", Toast.LENGTH_SHORT).show();

                            }
                        }


                    });

                }
            }
        });


    }


    private Boolean validate() {
        Boolean result = false;

        String password = UserPassword.getText().toString();
        String email = UserEmail.getText().toString();

        if (  password.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "plzz enter perfectly", Toast.LENGTH_SHORT).show();
        } else {
            result = true;
        }

        return result;

    }
}