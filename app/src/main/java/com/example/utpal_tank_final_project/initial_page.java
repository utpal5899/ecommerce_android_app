package com.example.utpal_tank_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class initial_page extends AppCompatActivity {
    private static int timer =2700;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_page);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent  myIntent = new Intent(initial_page.this,MainActivity.class);
                startActivity(myIntent);
                finish();
            }
        },timer);

    }
}