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
import com.google.firebase.storage.StorageReference;
import com.mastercode.salvago.R;
import com.mastercode.salvago.adapters.Home_Companies;
import com.mastercode.salvago.database.Cloud;
import com.mastercode.salvago.database.Cloudfiles;
import com.mastercode.salvago.models.Company;

import java.util.ArrayList;
import java.util.List;

public class Fg_Home_Navigation extends Fragment implements ValueEventListener {

    int index;
    DatabaseReference ref;
    Home_Companies adapter;
    List<Company> companies;
    StorageReference storage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_recyclerview,container,false);
        index = getArguments().getInt("option");
        return init(v);
    }

    public View init(View v){
        RecyclerView rcv = v.findViewById(R.id.rcv);
        rcv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rcv.setHasFixedSize(true);

        companies = new ArrayList<>();
        String title = "";

        switch (index) {
            case 1:
                adapter = new Home_Companies(this.getContext(),companies,"restaurants");
                ref = new Cloud().getRestaurants();
                title = "Restaurantes";
                break;
            case 2:
                adapter = new Home_Companies(this.getContext(),companies,"tourist");
                ref = new Cloud().getTourist();
                title = "Turismo";
                break;
            case 3:
                adapter = new Home_Companies(this.getContext(),companies,"hotels");
                ref = new Cloud().getHotels();
                title = "Hospedajes";
                break;
            case 4:
                adapter = new Home_Companies(this.getContext(),companies,"shops");
                ref = new Cloud().getShops();
                title = "Tiendas";
                break;
            case 5:
                adapter = new Home_Companies(this.getContext(),companies,"services");
                ref = new Cloud().getServices();
                title = "Servicios";
                break;
        }

        rcv.setAdapter(adapter);
        getActivity().setTitle(title);
        storage = new Cloudfiles().getBanners();

        getData();
        return v;
    }

    public void getData(){
        companies.clear();
        ref.addListenerForSingleValueEvent(this);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        for(DataSnapshot d : dataSnapshot.getChildren()){
            final Company com = new Company();
            com.companyname = d.child("info").child("title").getValue().toString();
            com.descripcion = d.child("info").child("description").getValue().toString();

            if(d.child("info").child("telephones").exists()){
                com.telephone = d.child("info").child("telephones").child("0").getValue().toString();
            }

            com.id = d.getKey();
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