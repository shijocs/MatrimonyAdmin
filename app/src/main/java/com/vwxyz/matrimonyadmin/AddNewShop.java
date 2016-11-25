package com.vwxyz.matrimonyadmin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewShop extends AppCompatActivity {


    EditText shopName;
    EditText shopemail;
    EditText shopPassword;
    EditText shopAddress;
    EditText shopPhone;
    EditText shopLand;
    Button shopAdd;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String email;
    String password;
    String name;
    String address;
    String phone;
    String land;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_shop);
        database = FirebaseDatabase.getInstance();


    //    mAuth = FirebaseAuth.getInstance();

        shopName = (EditText) findViewById(R.id.shop_name);
        shopemail = (EditText) findViewById(R.id.shop_email);
      //  shopPassword = (EditText) findViewById(R.id.shop_password);
        shopAddress = (EditText) findViewById(R.id.shop_address);
        shopPhone = (EditText) findViewById(R.id.shop_phone);
        shopLand= (EditText) findViewById(R.id.shop_land);
        shopAdd = (Button) findViewById(R.id.btn_addShop);

        shopAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = shopemail.getText().toString().trim();
                 password = shopPassword.getText().toString().trim();
                name = shopName.getText().toString().trim();
                address = shopAddress.getText().toString().trim();
                phone = shopPhone.getText().toString().trim();
                land = shopLand.getText().toString().trim();

//                myRef = database.getReference("Shops/"+ name);
//                myRef.child("shop_name").setValue(name);
//                myRef.child("shop_email").setValue(email);
//                myRef.child("shop_address").setValue(address);
//                myRef.child("Phone").setValue(phone);
//                myRef.child("Land_Mark").setValue(land);



                Intent i =new Intent(AddNewShop.this,MainActivity.class);
                startActivity(i);
                //registerShop(email,password,name,address);
            }


        });
    }

}