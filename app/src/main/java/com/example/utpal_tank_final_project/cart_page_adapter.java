package com.example.utpal_tank_final_project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class cart_page_adapter extends RecyclerView.Adapter<cart_page_adapter.ViewHolder> {

    ArrayList<product_upload> list;
    Context context;

    public cart_page_adapter(ArrayList<product_upload> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.cart_recycleview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        product_upload model= list.get(position);
        Picasso.get().load(model.getProduct_image()).placeholder(R.drawable.amazonlogo).into(holder.product_image);
        holder.product_name.setText(model.getProduct_name());
        holder.product_price.setText(model.getProduct_price());
        holder.quantity.setText(""+model.getQuantity());










//        holder.delete.setOnClickListener((view -> {
//            AlertDialog.Builder builder=new AlertDialog.Builder(holder.product_image.getContext());
//
//
//            Log.i("TAG", "onBindViewHolder: item deletedddddddddddddddddddddddddddddddddddddddddddddd"+list);
//            int removeIndex=0;
//            list.remove(removeIndex);
//            Log.i("TAG", "onBindViewHolder: item deletedddddddddddddddddddddddddddddddddddddddddddddd"+list);
//
//
//
////
//
//
//
//
//
//
//
//            builder.setTitle("delete product from cart");
//            builder.setMessage("delete?");
//
//
//
//
//            builder.setPositiveButton("yes",((dialogInterface, i) -> {
//                FirebaseDatabase.getInstance().getReference().child("product").child(String.valueOf(getItemId(position))).removeValue();
//
//            }));
//
//            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//
//                }
//            });
//
//        }));




    }




    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView product_name,product_price,quantity;
        ImageView product_image;
        Button delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            product_name =  itemView.findViewById(R.id.name_product);
            product_price =  itemView.findViewById(R.id.price_product);
             product_image=  itemView.findViewById(R.id.img_product);
            quantity=  itemView.findViewById(R.id.qtynum);
            delete=itemView.findViewById(R.id.remove);



        }
    }



}
