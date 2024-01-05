package com.example.utpal_tank_final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class home_page extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<product_upload> recycleList;

    ViewPager2 vPager;
    RecyclerView.Adapter adapter;
    ImageView cart_btn;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
       cart_btn=findViewById(R.id.header_cart);
        recyclerView = findViewById(R.id.recycleView);
        recycleList=new ArrayList<>();
        firebaseDatabase=FirebaseDatabase.getInstance();

        home_page_adapter reclerAdapter=new home_page_adapter(recycleList,getApplicationContext());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
      recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
   recyclerView.setNestedScrollingEnabled(false);
   recyclerView.setAdapter(reclerAdapter);


//   cart

        cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(home_page.this, cart_page.class);
                startActivity(intent);
            }
        });





   firebaseDatabase.getReference().child("product").addListenerForSingleValueEvent(new ValueEventListener() {
       @Override
       public void onDataChange(@NonNull DataSnapshot snapshot) {
           for (DataSnapshot dataSnapshot : snapshot.getChildren()){
               product_upload product_upload= dataSnapshot.getValue(product_upload.class);
                recycleList.add(product_upload);


           }

           reclerAdapter.notifyDataSetChanged();



       }

       @Override
       public void onCancelled(@NonNull DatabaseError error) {

       }

   });

// after adding grid view application gets slow
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

    }
}