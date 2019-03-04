package com.mastercode.salvago.fragments;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mastercode.salvago.R;
import com.mastercode.salvago.adapters.Rcv_Menu_Adapter;
import com.mastercode.salvago.database.Cloud;
import com.mastercode.salvago.models.Product;

import java.util.ArrayList;
import java.util.List;

public class Fg_Dashboard_Catalog extends Fragment implements View.OnClickListener, ValueEventListener {

    Rcv_Menu_Adapter adapter;
    DatabaseReference ref;
    RecyclerView rcv;
    Button btnadd;
    List<Product> products;
    EditText tvPrice, tvDesc, tvTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_dashboard_catalog, container,false);
        return init(v);
    }

    private View init(View v){
        products = new ArrayList<>();
        rcv = v.findViewById(R.id.rcv);
        tvTitle = v.findViewById(R.id.menuproduct);
        tvDesc = v.findViewById(R.id.menudescript);
        tvPrice = v.findViewById(R.id.menuprice);

        btnadd = v.findViewById(R.id.addtomenu);
        btnadd.setOnClickListener(this);

        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));

        ref = new Cloud().getMenuOfCompany("restaurants", "@testcompany2");

        adapter = new Rcv_Menu_Adapter(getContext(), products);
        rcv.setAdapter(adapter);
        ref.addListenerForSingleValueEvent(this);
        return v;
    }

    @Override
    public void onClick(View v) {

        try{
            Product p = new Product();
            p.title = tvTitle.getText().toString();
            p.description = tvDesc.getText().toString();
            p.price = Double.parseDouble(tvPrice.getText().toString());

            if(!products.contains(p)){
                products.add(p);
                adapter.notifyDataSetChanged();
                Snackbar.make(v,"Producto Agregado", Snackbar.LENGTH_LONG).show();
            }

        }catch (Exception e){
            Snackbar.make(v,"Datos incompletos", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        for(DataSnapshot d : dataSnapshot.getChildren()){
            Product p = new Product();
            p.title = d.child("title").getValue().toString();
            p.description = d.child("description").getValue().toString();
            p.price = Double.parseDouble(d.child("price").getValue().toString());
            products.add(p);
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
