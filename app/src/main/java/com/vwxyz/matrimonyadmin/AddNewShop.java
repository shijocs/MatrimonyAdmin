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

    private FirebaseAuth mAuth;
    EditText shopName;
    EditText shopemail;
    EditText shopPassword;
    EditText shopAddress;
    Button shopAdd;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String email;
    String password;
    String name;
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_shop);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Shops");

        mAuth = FirebaseAuth.getInstance();

        shopName = (EditText) findViewById(R.id.shop_name);
        shopemail = (EditText) findViewById(R.id.shop_email);
        shopPassword = (EditText) findViewById(R.id.shop_password);
        shopAddress = (EditText) findViewById(R.id.shop_address);
        shopAdd = (Button) findViewById(R.id.btn_addShop);

        shopAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = shopemail.getText().toString().trim();
                 password = shopPassword.getText().toString().trim();
                name = shopName.getText().toString().trim();
                address = shopAddress.getText().toString().trim();
                registerShop(email,password,name,address);
            }


        });
    }
    private void registerShop(final String email, String password, final String name, final String address) {



        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(AddNewShop.this, "Failed add new shop",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(AddNewShop.this, "suucessfully added new user.",
                                    Toast.LENGTH_SHORT).show();
                            myRef.push();
                            myRef.child("shop_name").setValue(name);
                            myRef.child("shop_email").setValue(email);
                            myRef.child("shop_address").setValue(address);
                            Intent i =new Intent(AddNewShop.this,MainActivity.class);
                            startActivity(i);
                        }

                        // ...
                    }
                });
    }
}