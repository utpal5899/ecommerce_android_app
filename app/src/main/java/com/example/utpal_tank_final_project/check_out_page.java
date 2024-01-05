package com.example.utpal_tank_final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class check_out_page extends AppCompatActivity {

    TextView single_product_name,single_product_price,product_price_total;
    TextView f_name,l_name,email_id,phone_number,address,postal_code,card_number,card_holder,cvv_code;
    Button place_order,add_cart ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out_page);


//        personal info
        f_name=findViewById(R.id.f_name);
        l_name=findViewById(R.id.l_name);
        email_id=findViewById(R.id.email_id);
        phone_number=findViewById(R.id.phone_number);
        address=findViewById(R.id.address);
        postal_code=findViewById(R.id.postal_code);
        card_number=findViewById(R.id.card_number);
        card_holder=findViewById(R.id.card_holder);
        cvv_code=findViewById(R.id.cvv_code);














        single_product_name=findViewById(R.id.product_name_check);
        single_product_price=findViewById(R.id.product_price_check);
        product_price_total=findViewById(R.id.product_price_total);
        place_order=findViewById(R.id.place_order);
        Double price= Double.valueOf(getIntent().getStringExtra("single_product_price"));
        price = Double.valueOf(Math.round(price * 100));
        price = price/100;
        single_product_name.setText(getIntent().getStringExtra("single_product_name"));
        single_product_price.setText(""+price);
          Double number= Double.valueOf(getIntent().getStringExtra("single_product_price"))*0.13+Double.valueOf(getIntent().getStringExtra("single_product_price"));
        number = Double.valueOf(Math.round(number * 100));
        number = number/100;
        Log.i("TAG", "onCreate: "+ number);
        product_price_total.setText(""+number);


        place_order.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          String validNumber = "^[+]?[0-9]{8,15}$";
          String validcvv = "^[0-9]{2,5}$";
          String validemail=   "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                  "\\@" +
                  "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                  "(" +
                  "\\." +
                  "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                  ")+";

          if(f_name.getText().toString().equals(""))
          {
              Toast.makeText(check_out_page.this, " first name is required", Toast.LENGTH_SHORT).show();


          } else if ( l_name.getText().toString().equals("")) {

              Toast.makeText(check_out_page.this, " last name is required", Toast.LENGTH_SHORT).show();


          } else if (address.getText().toString().equals("")){

              Toast.makeText(check_out_page.this, " address is required", Toast.LENGTH_SHORT).show();



          }
          else if (postal_code.getText().toString().equals("")){

              Toast.makeText(check_out_page.this, " postal code is required", Toast.LENGTH_SHORT).show();


          }
          else if (card_holder.getText().toString().equals("")){

              Toast.makeText(check_out_page.this, "card holder name is required", Toast.LENGTH_SHORT).show();


          } else if (cvv_code.getText().toString().equals("")) {
              Toast.makeText(check_out_page.this, "cvv number is required", Toast.LENGTH_SHORT).show();

          }
          else if (card_number.getText().toString().equals("")) {
              Toast.makeText(check_out_page.this, "card number is required", Toast.LENGTH_SHORT).show();

          }
          else if (email_id.getText().toString().equals("")) {
              Toast.makeText(check_out_page.this, "email is required", Toast.LENGTH_SHORT).show();

          }
          else if (!email_id.getText().toString().matches(validemail)) {
              Toast.makeText(check_out_page.this, "email is not valid", Toast.LENGTH_SHORT).show();

          }
          else if (phone_number.getText().toString().equals("") ) {
              Toast.makeText(check_out_page.this, "phone number is required", Toast.LENGTH_SHORT).show();

          } else if (!phone_number.getText().toString().matches(validNumber)) {
              Toast.makeText(check_out_page.this, "phone number is not valid", Toast.LENGTH_SHORT).show();

          }
          else if (!card_number.getText().toString().matches(validNumber)) {
              Toast.makeText(check_out_page.this, "card number is not valid", Toast.LENGTH_SHORT).show();

          }
          else if (!cvv_code.getText().toString().matches(validcvv)) {
              Toast.makeText(check_out_page.this, "cvv number is not valid", Toast.LENGTH_SHORT).show();

          }
          else {

              Intent intent=new Intent(check_out_page.this, thankyou_page.class);
              startActivity(intent);

          }




      }
  });



    }
}