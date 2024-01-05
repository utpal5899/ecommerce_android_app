package com.example.utpal_tank_final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class singleProduct extends AppCompatActivity {


    TextView single_product_name,single_product_price,single_product_description,qny_num;
    ImageView single_product_image,cart_btn;
    Button buy_product,add_cart,minus_btn,plus_btn;


    int count=1;




    Uri ImageUri;

    RelativeLayout relativeLayout;

    private FirebaseDatabase database;
    private FirebaseStorage firebaseStorage;
    private DatabaseReference reference;

    ProgressDialog dialog;







    //    getting error if add Override
//    Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product);
        reference= FirebaseDatabase.getInstance().getReference("add_cart");
        String id_cart= reference.push().getKey();




        single_product_name=findViewById(R.id.single_product_name);
        single_product_price=findViewById(R.id.single_product_price);
        single_product_description=findViewById(R.id.single_product_discription);
         buy_product=findViewById(R.id.buy_product_btn);
         add_cart=findViewById(R.id.add_cart);
        single_product_image=findViewById(R.id.single_product);
        minus_btn=findViewById(R.id.minus);
        plus_btn=findViewById(R.id.plus);
        qny_num=findViewById(R.id.qny_num);



        cart_btn= findViewById(R.id.header_cart);
        Picasso.get().load(getIntent().getStringExtra("single_product"))
                .placeholder(R.drawable.amazonheader).into(single_product_image);

         Double price=getIntent().getDoubleExtra("single_product_vaal",0);
        single_product_name.setText(getIntent().getStringExtra("single_product_name"));
        single_product_price.setText(getIntent().getStringExtra("single_product_price"));
        single_product_name.setText(getIntent().getStringExtra("single_product_name"));
        single_product_description.setText(getIntent().getStringExtra("single_product_discription"));







plus_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
    count++;
    qny_num.setText(""+count);
    }
});



        minus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count<=1)
                {
                    count=1;
                }
                else {
                    count--;
                }

                qny_num.setText(""+count);
            }
        });





















        cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(singleProduct.this, cart_page.class);
                startActivity(intent);
            }
        });




        buy_product.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {

          Double price=getIntent().getDoubleExtra("single_product_vaal",0)*count;

          Intent intent=new Intent(singleProduct.this, check_out_page.class);
          intent.putExtra("single_product_price",price+"");
          intent.putExtra("single_product_name",getIntent().getStringExtra("single_product_name"));
           intent.putExtra("single_product_vaal",price);
          Log.i("sdfsdf", "onClick: dsssssssssssss"+getIntent().getDoubleExtra("single_product_vaal",0)*count);
          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          startActivity(intent);
      }
  });

        add_cart.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                product_upload model= new product_upload();

                model.setProduct_name(getIntent().getStringExtra("single_product_name"));
                 model.setProduct_price(String.valueOf(getIntent().getDoubleExtra("single_product_vaal",0)*count));
                 model.setQuantity(Integer.parseInt(qny_num.getText().toString()));
                 reference.child(id_cart).setValue(model);

                    Toast.makeText(singleProduct.this,"added to cart",Toast.LENGTH_SHORT).show();

//                FirebaseDatabase database= FirebaseDatabase.getInstance();
//                DatabaseReference ref=database.getReference("name");
//                ref.setValue(getIntent().getStringExtra("single_product_price"));


            }
        });


//        add to cart












    }
}