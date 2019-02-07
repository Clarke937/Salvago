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

    public DatabaseReference getRestaurantInfo(String name){
        return ref.child("companies").child("restaurants").child(name).child("info");
    }

    public DatabaseReference getVisitsOfCompany(String companytype, String company){
        return ref.child("companies").child(companytype).child(company).child("visits");
    }

}
