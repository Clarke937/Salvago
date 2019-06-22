package com.mastercode.salvago.fragments;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.mastercode.salvago.Fanpage;
import com.mastercode.salvago.R;
import com.mastercode.salvago.adapters.Adapter_Listview_Void;
import com.mastercode.salvago.adapters.Fanpage_Promos;
import com.mastercode.salvago.database.Cloud;
import com.mastercode.salvago.database.Cloudfiles;
import com.mastercode.salvago.models.Promo;

import java.util.ArrayList;
import java.util.List;

public class Fg_Fanpage_Promos extends Fragment implements ValueEventListener {

    ListView list;
    List<Promo> promos;
    DatabaseReference ref;
    StorageReference storage;
    Fanpage_Promos adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_fanpageinfo,container,false);
        return init(v);
    }


    private View init(View v){

        promos = new ArrayList<>();
        adapter = new Fanpage_Promos(this.getContext(),promos);
        list = v.findViewById(R.id.list);
        list.setAdapter(adapter);
        ref = new Cloud().getPromosOfCompany(Fanpage.companytype,Fanpage.companyid);
        ref.addListenerForSingleValueEvent(this);
        return v;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        promos.clear();
        for (DataSnapshot d : dataSnapshot.getChildren()) {

            final Promo p = new Promo();

            p.description = d.child("description").getValue().toString();
            p.date_fin = Long.parseLong(d.child("findate").getValue().toString());
            String imagen = d.child("imagen").getValue().toString();
            p.date_ini = Long.parseLong(d.child("inidate").getValue().toString());
            p.price_promo = Double.parseDouble(d.child("promoprice").getValue().toString());
            p.price_normal = Double.parseDouble(d.child("normalprice").getValue().toString());
            p.title = d.child("title").getValue().toString();

            storage = new Cloudfiles().getCompanyPromos(Fanpage.companyid, imagen);
            storage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    p.pic = uri;
                    adapter.notifyDataSetChanged();
                }
            });

            promos.add(p);
            adapter.notifyDataSetChanged();
        }

        if(promos.size() == 0) showVoidView();
    }


    void showVoidView(){
        list.setAdapter(new Adapter_Listview_Void(this.getContext()));
        list.setClickable(false);
    }


    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}

