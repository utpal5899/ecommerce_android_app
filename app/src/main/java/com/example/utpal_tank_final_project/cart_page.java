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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

public class cart_page extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<product_upload> recycleList;

    ViewPager2 vPager;
    RecyclerView.Adapter adapter;
    FirebaseDatabase firebaseDatabase;
    TextView total_cart_sum,quantity;
    Button place_order;
    float sum=0;
    String items="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page);

        total_cart_sum = findViewById(R.id.total_price);


        recyclerView = findViewById(R.id.recycleView_cart);
        recycleList=new ArrayList<>();
        place_order=  findViewById(R.id.place_order);
        firebaseDatabase=FirebaseDatabase.getInstance();

        cart_page_adapter reclerAdapter=new cart_page_adapter(recycleList,getApplicationContext());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(reclerAdapter);


        firebaseDatabase.getReference().child("add_cart").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DecimalFormat decimalFormat = new DecimalFormat("#.##");


                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Map<String,Object> map=(Map<String, Object>) dataSnapshot.getValue();
                    Object price = map.get("product_price");
                    float priceval=Float.parseFloat(String.valueOf(price));
                    sum=  sum+priceval ;
                    Object item = map.get("product_name");
                    items=items+item+", ";
                    product_upload product_upload= dataSnapshot.getValue(product_upload.class);
                    recycleList.add(product_upload);

                }

                total_cart_sum.setText("$"+Float.valueOf(decimalFormat.format(sum)) );
//                total_cart_sum.setText(sum);
                Log.i("anssssss", "onDataChange: ///////////////////////////////////////////////////////////////////////"+Float.valueOf(decimalFormat.format(sum)));

                reclerAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(cart_page.this, check_out_page.class);

                intent.putExtra("single_product_price",""+sum);
                intent.putExtra("single_product_name",""+items);

                Log.i("", "onClick: "+items);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

    }
}