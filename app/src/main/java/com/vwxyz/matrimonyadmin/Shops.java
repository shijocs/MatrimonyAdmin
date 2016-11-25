package com.vwxyz.matrimonyadmin;

/**
 * Created by mboxuser on 11/25/2016.
 */

public class Shops {

    String name;
    String email;
    String address;
    String phone;
    String location;

    public Shops() {
    }

    public Shops(String name, String email, String address, String phone, String location) {
        this.address = address;
        this.email = email;
        this.location = location;
        this.name = name;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
