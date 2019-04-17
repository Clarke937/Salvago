package com.mastercode.salvago.fragments;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.mastercode.salvago.R;
import com.mastercode.salvago.adapters.Adapter_Dashboard_Pics;
import com.mastercode.salvago.database.Cloud;
import com.mastercode.salvago.database.Cloudfiles;
import com.mastercode.salvago.tools.MySession;

import java.util.ArrayList;
import java.util.List;

public class Fg_Dashboard_Photos extends Fragment implements ValueEventListener {

    GridView grid;
    List<String> urls;
    List<Uri> uris;
    DatabaseReference ref;
    StorageReference storage;
    FloatingActionButton fab;
    String companytype,companyid;
    Adapter_Dashboard_Pics adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_photogallery,container,false);
        return init(v);
    }

    public View init(View v) {
        urls = new ArrayList<>();
        uris = new ArrayList<>();
        fab = v.findViewById(R.id.fab);
        grid = v.findViewById(R.id.gdv);

        adapter = new Adapter_Dashboard_Pics(this.getContext(),uris);
        grid.setAdapter(adapter);

        companytype = MySession.dashcompany.companytype;
        companyid = MySession.dashcompany.id;

        ref = new Cloud().getPicsOfCompany(companytype,companyid);
        ref.addListenerForSingleValueEvent(this);
        return v;
    }


    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        urls.clear();
        for (DataSnapshot d : dataSnapshot.getChildren()){
            String u = d.getValue().toString();
            urls.add(u);
            Log.e("PHOTO", u.trim());
        }
        DownloadPics();
    }

    public void DownloadPics()
    {
        storage = new Cloudfiles().getCompanyPics(companyid);
        //uris.clear();
        for(String u : urls){
            storage.child(u).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    uris.add(uri);
                    Log.e("PHOTO URI:",uri.toString());
                    adapter.notifyDataSetChanged();
                }
            });
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
