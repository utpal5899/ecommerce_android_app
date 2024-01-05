package com.example.utpal_tank_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class thankyou_page extends AppCompatActivity {

    Button home_page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thankyou_page);

        home_page=findViewById(R.id.home_page_btn);


        home_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(thankyou_page.this,home_page.class);
                startActivity(intent);
            }

        });



    }
}