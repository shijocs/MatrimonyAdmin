package com.vwxyz.matrimonyadmin;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView textView;
    FirebaseUser user;
    DatabaseReference db;
    FireBaseHelper helper;
    CustomAdapater adapater;
    ListView lv;

    EditText shopName;
    EditText shopemail;
    EditText shopAddress;
    EditText shopPhone;
    EditText shopLand;
    Button shopAdd;

    String email;
    String password;
    String name;
    String address;
    String phone;
    String land;
    DataSnapshot snapShot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.shop_list);


        db= FirebaseDatabase.getInstance().getReference();
        helper = new FireBaseHelper(db);

        user = FirebaseAuth.getInstance().getCurrentUser();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i =new Intent(MainActivity.this,AddNewShop.class);
//                startActivity(i);
                showDialogue();

            }
        });
        Shops s = new Shops();
        if(helper.save(s)) {
            adapater = new CustomAdapater(this, helper.retrieve());
            lv.setAdapter(adapater);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void showDialogue() {


       final Dialog d = new Dialog(this);
        d.setTitle("Save To Firebase");
        d.setContentView(R.layout.activity_add_new_shop);
        shopName = (EditText) d.findViewById(R.id.shop_name);
        shopemail = (EditText) d.findViewById(R.id.shop_email);
        shopAddress = (EditText) d.findViewById(R.id.shop_address);
        shopPhone = (EditText) d.findViewById(R.id.shop_phone);
        shopLand= (EditText) d.findViewById(R.id.shop_land);
        shopAdd = (Button) d.findViewById(R.id.btn_addShop);
        //SAVE
        shopAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //GET DATA
                email = shopemail.getText().toString().trim();
              //  password = shopPassword.getText().toString().trim();
                name = shopName.getText().toString().trim();
                address = shopAddress.getText().toString().trim();
                phone = shopPhone.getText().toString().trim();
                land = shopLand.getText().toString().trim();
                //SET DATA
                Shops s = new Shops();
                s.setName(name);
                s.setEmail(email);
                s.setAddress(address);
                s.setPhone(phone);
                s.setLocation(land);
                //SIMPLE VALIDATION
                if (name != null && name.length() > 0) {
                    //THEN SAVE
                    if (helper.save(s)) {
                        //IF SAVED CLEAR EDITXT
                        shopName.setText("");
                        shopemail.setText("");
                        shopAddress.setText("");
                         shopPhone.setText("");
                       shopLand.setText("");
                        adapater = new CustomAdapater(MainActivity.this, helper.retrieve());
                        lv.setAdapter(adapater);
                    }
                } else {
                   // Toast.makeText(MainActivity.this, "Name Must Not Be Empty", Toast.LENGTH_SHORT).show();
                }
               d.dismiss();
            }
        });
        d.show();



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
