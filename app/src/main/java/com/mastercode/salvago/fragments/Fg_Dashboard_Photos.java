package com.mastercode.salvago.fragments;

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

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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

public class Fg_Dashboard_Photos extends Fragment implements ValueEventListener, View.OnClickListener {

    List<Uri> uris;
    GridView photogrid;
    DatabaseReference ref;
    StorageReference storage;
    Adapter_Dashboard_Pics adapter;
    FloatingActionButton fab;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_dashboard_pics,container,false);
        return init(v);
    }

    public View init(View v) {
        uris = new ArrayList<>();
        photogrid = v.findViewById(R.id.gridpcisview);
        adapter = new Adapter_Dashboard_Pics(this.getContext(),uris);
        photogrid.setAdapter(adapter);

        ref = new Cloud().getPicsOfCompany(MySession.dashcompany.companytype,MySession.dashcompany.id);
        storage = new Cloudfiles().getCompanyPics(MySession.dashcompany.id);
        ref.addListenerForSingleValueEvent(this);
        return v;
    }


    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        for(DataSnapshot d : dataSnapshot.getChildren()){
            storage.child(d.getValue().toString()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    uris.add(uri);
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    @Override
    public void onClick(View v) {

    }
}
