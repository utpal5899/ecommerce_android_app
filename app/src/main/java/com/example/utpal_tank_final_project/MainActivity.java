package com.example.utpal_tank_final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {



    private EditText UserName;
    private EditText password;
    private TextView info,registerr;

    private Button upload_product;
    private Button Login;
    private int counter = 5;
    private FirebaseAuth mAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        UserName = (EditText) findViewById(R.id.etname);
        password = (EditText) findViewById(R.id.edpassword);
        info = (TextView) findViewById(R.id.edview);
        registerr = (TextView) findViewById(R.id.tvRegister);
        Login = (Button) findViewById(R.id.btn_login);
        info.setText("NO of attempts remaining: 5");








// to login  user
        Login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(validate()) {

                    String User_Email = UserName.getText().toString().trim();
                    String User_password = password.getText().toString().trim();

                    if (User_Email.equals("admin") && User_password.equals("admin")) {
                        Toast.makeText(MainActivity.this, "admin logged In ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, add_product.class);
                        startActivity(intent);

                    }

                else {
                    mAuth.signInWithEmailAndPassword(User_Email, User_password).addOnCompleteListener(task -> {


                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "login is done", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, home_page.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "login is failed", Toast.LENGTH_SHORT).show();
                            counter--;

                            info.setText("NO of attempts remaining: " + String.valueOf(counter));
                            if (counter == 0) {
                                Login.setEnabled(false);

                            }
                        }
                    });
                }
                }


            }
        });
        registerr.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this,registration.class);
                startActivity(intent);

            }
        });



    }
    private Boolean validate()
    {
        Boolean result = false;

        String word = password.getText().toString();
        String email = UserName.getText().toString();

        if ( word.isEmpty() || email.isEmpty())
        {
            Toast.makeText(MainActivity.this, "username and password are required", Toast.LENGTH_SHORT).show();
            counter--;

            info.setText("NO of attempts remaining: "+ String.valueOf(counter));
            if(counter==0){
                Login.setEnabled(false);
            }

        }
        else
        {
            result = true;
        }

        return result;
    }
}