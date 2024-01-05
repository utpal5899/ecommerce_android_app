package com.example.utpal_tank_final_project;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class home_page_adapter extends RecyclerView.Adapter<home_page_adapter.ViewHolder> {

ArrayList<product_upload> list;
Context context;

    public home_page_adapter(ArrayList<product_upload> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.home_recycleview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
             product_upload model= list.get(position);
        Picasso.get().load(model.getProduct_image()).placeholder(R.drawable.amazonlogo).into(holder.product_image);
     holder.product_name.setText(model.getProduct_name());
        holder.product_price.setText(model.getProduct_price());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(context, singleProduct.class);

                Intent intent=new Intent(context, singleProduct.class);
 

                intent.putExtra("single_product",model.getProduct_image());
                intent.putExtra("single_product_price",model.getProduct_price());
                intent.putExtra("single_product_discription",model.getProduct_description());
                intent.putExtra("single_product_name",model.getProduct_name());
                intent.putExtra("single_product_vaal",model.getVaal());
                  Log.i("", String.valueOf(model.getVaal()));

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView product_name,product_price;
        ImageView product_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            product_name =  itemView.findViewById(R.id.name_product);
            product_price =  itemView.findViewById(R.id.price_product);
//            Product_description =   itemView.findViewById(R.id.product_description);

            product_image=  itemView.findViewById(R.id.img_product);



        }
    }



}
