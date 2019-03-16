package com.mastercode.salvago.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mastercode.salvago.R;
import com.mastercode.salvago.adapters.Home_Companies;
import com.mastercode.salvago.database.Cloud;
import com.mastercode.salvago.models.Company;
import com.mastercode.salvago.tools.MySession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Fg_Search_Result extends Fragment implements ValueEventListener {

    RecyclerView rcv;
    DatabaseReference searchRef;
    Home_Companies adapter;
    List<String> companiesids;
    List<Company> companies;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_recyclerview, container,false);
        getActivity().setTitle("Resultados");
        return init(v);
    }

    private View init(View v){
        companies = new ArrayList<>();
        companiesids = new ArrayList<>();
        adapter = new Home_Companies(getContext(), companies);
        rcv = v.findViewById(R.id.rcv);
        rcv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rcv.setHasFixedSize(true);
        rcv.setAdapter(adapter);
        searchRef = new Cloud().getSearch();
        searchRef.addListenerForSingleValueEvent(this);
        return v;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        companiesids.clear();

        for (DataSnapshot d: dataSnapshot.getChildren()) {
            String title = d.child("title").getValue().toString().toLowerCase().trim();
            String searchword = MySession.busqueda.search.trim().toLowerCase();

            if(!companiesids.contains(d.getKey())){
                if(title.equals(searchword) || title.contains(searchword)){
                    companiesids.add(d.getKey());
                }else{
                    List<String> words = Arrays.asList(MySession.busqueda.search.trim().toLowerCase().split(","));
                    for (DataSnapshot dtag: d.child("tags").getChildren()) {
                        String tag = dtag.getValue().toString();
                        if(words.contains(tag)) companiesids.add(d.getKey());
                    }
                }
            }
        }

        LoadCompanies();
    }


    private void LoadCompanies(){
        DatabaseReference companiesRef = new Cloud().getCompanies();
        companiesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dtp : dataSnapshot.getChildren()){
                    for (DataSnapshot id: dtp.getChildren()) {
                        if(companiesids.contains(id)) AddCompany(id);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void AddCompany(DataSnapshot d){
        Company c = new Company();
        c.id = d.getKey();
        c.companytype = d.getRef().getKey();
        c.companyname = d.child("info").child("title").getValue().toString();
        c.descripcion = d.child("info").child("description").getValue().toString();
        if(!companies.contains(c)) companies.add(c);


        //adapter.notifyDataSetChanged();
    }


    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
