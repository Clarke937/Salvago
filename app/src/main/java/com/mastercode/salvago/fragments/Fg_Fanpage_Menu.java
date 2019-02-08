package com.mastercode.salvago.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mastercode.salvago.Fanpage;
import com.mastercode.salvago.R;
import com.mastercode.salvago.adapters.Fanpage_menu;
import com.mastercode.salvago.database.Cloud;
import com.mastercode.salvago.models.Product;

import java.util.ArrayList;
import java.util.List;

public class Fg_Fanpage_Menu extends Fragment implements ValueEventListener {

    ListView listview;
    List<Product> products;
    DatabaseReference ref;
    Fanpage_menu adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_fanpageinfo,container,false);
        return init(v);
    }

    public View init(View v){

        products = new ArrayList<>();
        listview = v.findViewById(R.id.list);
        adapter = new Fanpage_menu(this.getContext(),products);
        listview.setAdapter(adapter);

        ref = new Cloud().getMenuOfCompany(Fanpage.companytype,Fanpage.companyid);
        ref.addListenerForSingleValueEvent(this);

        return v;
    }


    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        products.clear();
        for (DataSnapshot d : dataSnapshot.getChildren()){
            Product p = new Product();
            p.title = d.child("title").getValue().toString();
            p.description = d.child("description").getValue().toString();
            p.price = Double.parseDouble(d.child("price").getValue().toString());
            products.add(p);
            Log.e("PRODUCT",p.title);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        FirebaseCrash.report(databaseError.toException());
    }
}
