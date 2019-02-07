package com.mastercode.salvago.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mastercode.salvago.R;
import com.mastercode.salvago.adapters.Home_Companies;
import com.mastercode.salvago.database.Cloud;
import com.mastercode.salvago.database.Cloudfiles;
import com.mastercode.salvago.models.Company;

import java.util.ArrayList;
import java.util.List;

public class Fg_Nav_Restaurant extends Fragment implements ValueEventListener {

    DatabaseReference ref;
    Home_Companies adapter;
    List<Company> companies;
    StorageReference storage;
    static int counter = 0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_recyclerview,container,false);
        return init(v);
    }

    public View init(View v){
        RecyclerView rcv = v.findViewById(R.id.rcv);
        rcv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rcv.setHasFixedSize(true);

        companies = new ArrayList<>();
        adapter = new Home_Companies(this.getContext(),companies);
        rcv.setAdapter(adapter);

        ref = new Cloud().getRestaurants();
        storage = new Cloudfiles().getBanners();

        getData();
        return v;
    }

    public void getData(){
        ref.addListenerForSingleValueEvent(this);

    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        companies.clear();
        counter = 0;

        for(DataSnapshot d : dataSnapshot.getChildren()){
            final Company com = new Company();
            com.companyname = d.child("info").child("title").getValue().toString();
            com.descripcion = d.child("info").child("description").getValue().toString();
            com.city = Integer.parseInt(d.child("info").child("location").child("city").getValue().toString());

            storage.child(d.getKey() + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    com.banner = uri;
                    adapter.notifyDataSetChanged();
                }
            });
            companies.add(com);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
