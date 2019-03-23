package com.mastercode.salvago.fragments;

import android.net.Uri;
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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.mastercode.salvago.R;
import com.mastercode.salvago.adapters.Adapter_Promos;
import com.mastercode.salvago.database.Cloud;
import com.mastercode.salvago.database.Cloudfiles;
import com.mastercode.salvago.models.Promo;
import com.mastercode.salvago.tools.Statictools;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Fg_Nav_Promos extends Fragment implements ValueEventListener {

    RecyclerView rcv;
    List<Promo> promos;
    DatabaseReference ref;
    Adapter_Promos adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_recyclerview,container,false);
        getActivity().setTitle("Promociones");
        return init(v);
    }

    public View init(View v){

        Calendar now = Calendar.getInstance();
        now.set(Calendar.DAY_OF_MONTH, 18);
        now.set(Calendar.MONTH, 1);
        now.set(Calendar.YEAR, 2019);
        Log.e("Tomorrow", now.getTimeInMillis()+ "");

        promos = new ArrayList<>();
        adapter = new Adapter_Promos(promos, getContext());

        rcv = v.findViewById(R.id.rcv);
        rcv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rcv.setHasFixedSize(true);
        rcv.setAdapter(adapter);

        ref = new Cloud().getCompanies();
        ref.addListenerForSingleValueEvent(this);
        return v;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        for(DataSnapshot data : dataSnapshot.getChildren()){
            for(DataSnapshot da : data.getChildren()){
                if(da.child("promos").exists()){
                    for (DataSnapshot d : da.child("promos").getChildren()){

                        long expira = Long.parseLong(d.child("findate").getValue().toString());

                        if(!Statictools.DateExpired(expira)){
                            final Promo pro = new Promo();
                            pro.id = d.getKey();
                            pro.description = d.child("description").getValue().toString();
                            pro.title = d.child("title").getValue().toString();
                            pro.price_promo = Double.parseDouble(d.child("promoprice").getValue().toString());
                            pro.company = da.child("info").child("title").getValue().toString();
                            pro.date_fin = expira;

                            String url = d.child("imagen").getValue().toString();
                            StorageReference storage = new Cloudfiles().getCompanyPromos(da.getKey(), url);

                            storage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    pro.pic = uri;
                                    adapter.notifyDataSetChanged();
                                }
                            });
                            promos.add(pro);
                        }

                    }
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
