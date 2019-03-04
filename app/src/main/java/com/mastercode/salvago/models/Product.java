package com.mastercode.salvago.models;

import android.net.Uri;

public class Product {

    public String title;
    public String description;
    public double price;
    public Uri picture;


    public Product(){

    }

    public Product(String title, String description, double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }


    @Override
    public boolean equals(Object obj) {
        boolean ok = (this.title.equals(((Product)obj).title)) ? true : false;
        return ok;
    }
}
