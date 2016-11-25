package com.vwxyz.matrimonyadmin;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by mboxuser on 11/25/2016.
 */

public class FireBaseHelper {

    DatabaseReference db;
    Boolean saved;

     ArrayList<Shops> shops=new ArrayList<>();
    /*
 PASS DATABASE REFRENCE
  */
    public FireBaseHelper(DatabaseReference db) {
        this.db = db;
    }

    //WRITE IF NOT NULL
    public Boolean save(Shops shops)
    {
        if(shops==null)
        {
            saved=false;
        }else
        {
            try
            {
                db.child("Shops").push().setValue(shops);
                saved=true;
            }catch (DatabaseException e)
            {
                e.printStackTrace();
                saved=false;
            }
        }
        return saved;
    }

    //IMPLEMENT FETCH DATA AND FILL ARRAYLIST
    private void fetchData(DataSnapshot dataSnapshot)
    {
        shops.clear();
        for (DataSnapshot ds : dataSnapshot.getChildren())
        {
            Shops shop=ds.getValue(Shops.class);
           shops.add(shop);
        }
    }
    //RETRIEVE
    public ArrayList<Shops> retrieve()
    {

        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                fetchData(dataSnapshot);
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return shops;
    }
}
