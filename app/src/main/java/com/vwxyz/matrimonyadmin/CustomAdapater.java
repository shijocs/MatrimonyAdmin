package com.vwxyz.matrimonyadmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by mboxuser on 11/25/2016.
 */

public class CustomAdapater extends BaseAdapter {

    Context c;
    ArrayList<Shops> shops;
    public CustomAdapater(Context c, ArrayList<Shops> shops) {
        this.c = c;
        this.shops = shops;
    }
    @Override
    public int getCount() {
        return shops.size();
    }
    @Override
    public Object getItem(int position) {
        return shops.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.shop_list,parent,false);
        }
        TextView nameTxt= (TextView) convertView.findViewById(R.id.shopName);
        TextView phoneTxt= (TextView) convertView.findViewById(R.id.shopLocation);
        TextView locTxt= (TextView) convertView.findViewById(R.id.shopPhone);
        final Shops s= (Shops) this.getItem(position);
        nameTxt.setText("NAME : "+s.getName());
        phoneTxt.setText("PHONE NO: "+s.getPhone());
        locTxt.setText("Location : "+s.getLocation());
        //ONITECLICK
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c,s.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}
