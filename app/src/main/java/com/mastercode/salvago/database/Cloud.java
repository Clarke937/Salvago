package com.mastercode.salvago.database;
import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Cloud {

    DatabaseReference ref;

    public Cloud(){
        FirebaseApp app = FirebaseApp.getInstance("secondary");
        FirebaseDatabase db = FirebaseDatabase.getInstance(app);
        ref = db.getReference();
    }

    public DatabaseReference getAppInfo(){
        return ref.child("app");
    }

    public DatabaseReference getRestaurants(){
        return ref.child("companies").child("restaurants");
    }

    public DatabaseReference getHotels(){
        return ref.child("companies").child("hotels");
    }

    public DatabaseReference getServices(){
        return ref.child("companies").child("services");
    }

    public DatabaseReference getShops(){
        return ref.child("companies").child("shops");
    }

    public DatabaseReference getTourist(){
        return ref.child("companies").child("tourist");
    }

    public DatabaseReference getRestaurantInfo(String name){
        return ref.child("companies").child("restaurants").child(name).child("info");
    }

    public DatabaseReference getVisitsOfCompany(String companytype, String company){
        return ref.child("companies").child(companytype).child(company).child("visits");
    }

    public DatabaseReference getMenuOfCompany(String companytype, String company){
        return ref.child("companies").child(companytype).child(company).child("menu");
    }

    public DatabaseReference getPicsOfCompany(String type,String company){
        return ref.child("companies").child(type).child(company).child("pics");
    }

    public DatabaseReference getLocationsOfCompany(String type,String company){
        return ref.child("companies").child(type).child(company).child("locations");
    }

    public DatabaseReference getRatingOfCompany(String type, String company){
        return ref.child("companies").child(type).child(company).child("rating");
    }

    public DatabaseReference getSearch(){
        return ref.child("search");
    }




}
