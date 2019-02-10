package com.mastercode.salvago.database;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Cloudfiles {

    StorageReference ref;

    public Cloudfiles(){
        FirebaseApp app = FirebaseApp.getInstance("secondary");
        FirebaseStorage db = FirebaseStorage.getInstance(app);
        ref = db.getReference();
    }

    public StorageReference getBanners(){
        return ref.child("banners");
    }

    public StorageReference getCompanyBanner(String id){
        return ref.child("banners").child(id);
    }

    public StorageReference getCompanyPics(String companyid){
        return ref.child("companies").child(companyid);
    }

}
