package com.example.utpal_tank_final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;


import android.os.Bundle;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class view_pager extends AppCompatActivity {
    ViewPager2 vPager;
    RecyclerView.Adapter adapter;
    FirebaseDatabase firebaseDatabase;
    ArrayList<product_upload> cellList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);


        firebaseDatabase.getReference().child("product").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    product_upload product_upload= dataSnapshot.getValue(product_upload.class);
                    cellList.add(product_upload);


                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
        Log.i("sdfnjksufhguifsdkdkfbhsf", "onCreate: ????????????????????????????????????");
        vPager = findViewById(R.id.vPager);
        adapter = new home_page_adapter(cellList,getApplicationContext());
        vPager.setAdapter(adapter);

    }
}