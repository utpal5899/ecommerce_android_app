package com.example.utpal_tank_final_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class add_product extends AppCompatActivity {

    TextView product_name,Product_description,Product_price;
    ImageView upload_image,product_image;
    Button product_upload_btn;
    Uri ImageUri;

    RelativeLayout relativeLayout;

    private FirebaseDatabase database;
    private FirebaseStorage firebaseStorage;

     ProgressDialog dialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);


        database = FirebaseDatabase.getInstance();
        firebaseStorage=FirebaseStorage.getInstance();

   dialog= new ProgressDialog(this);
   dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
   dialog.setMessage("uploading...");
   dialog.setCancelable(false);
   dialog.setTitle("product uploading");
   dialog.setCanceledOnTouchOutside(false);

        product_name = (EditText) findViewById(R.id.product_name);
        Product_price = (EditText) findViewById(R.id.product_price);
        Product_description = (EditText) findViewById(R.id.product_description);
        upload_image =   findViewById(R.id.upload_image);
         product_image=  findViewById(R.id.product_image);
        product_upload_btn = (Button) findViewById(R.id.product_upload_btn);
        relativeLayout=findViewById(R.id.relative);
        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload_image();
                relativeLayout.setVisibility(View.VISIBLE);
                upload_image.setVisibility(View.GONE);
            }
        });


product_upload_btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        if(product_name.getText().toString().trim().equals("")

                 )
        {
            Toast.makeText(add_product.this,"must fill all the fields",Toast.LENGTH_SHORT).show();



        } else if ( Product_description.getText().toString().trim().equals("")) {

            Toast.makeText(add_product.this,"must fill all the fields",Toast.LENGTH_SHORT).show();


        } else if (Product_price.getText().toString().trim().equals("")) {


            Toast.makeText(add_product.this,"must fill all the fields",Toast.LENGTH_SHORT).show();


        } else{


            dialog.show();
            final StorageReference reference=firebaseStorage.getReference().child("product")
                    .child(System.currentTimeMillis()+"");
            reference.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            product_upload model= new product_upload();
                            model.setProduct_image(uri.toString());

                            model.setProduct_name(product_name.getText().toString());
                            model.setProduct_description(Product_description.getText().toString());
                            model.setProduct_price(Product_price.getText().toString());
                            model.setVaal(Double.valueOf(Product_price.getText().toString()));

                            database.getReference().child("product").push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    dialog.dismiss();
                                    Toast.makeText(add_product.this,"product uploaded",Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(add_product.this,"try again",Toast.LENGTH_SHORT).show();

                                }
                            });


                        }
                    });
                }
            });
        }


    }
});


    }

    private void upload_image() {

        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                Intent intent   = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,101);

            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Toast.makeText(add_product.this,"permission denied",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                 permissionToken.continuePermissionRequest();
            }
        }).check(); ;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//    if(requestCode ==101 && requestCode == RESULT_OK){
        ImageUri =data.getData();
        Log.i( "fsd", "onActivityResult: ImageUri");
        product_image.setImageURI(ImageUri);


//    }
    }
}