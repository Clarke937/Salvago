package com.mastercode.salvago.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.mastercode.salvago.Fanpage;
import com.mastercode.salvago.R;
import com.mastercode.salvago.adapters.Fanpage_Pics;
import com.mastercode.salvago.database.Cloud;
import com.mastercode.salvago.database.Cloudfiles;

import java.util.ArrayList;
import java.util.List;

public class Fg_Fanpage_Pics extends Fragment implements ValueEventListener {

    GridView grid;
    List<Uri> pics;
    Fanpage_Pics adapter;
    StorageReference storage;
    DatabaseReference ref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_fanpage_grid, container,false);
        return init(v);
    }

    public View init(View v){

        pics = new ArrayList<>();
        grid = v.findViewById(R.id.grid);

        adapter = new Fanpage_Pics(this.getContext(),pics);
        grid.setAdapter(adapter);

        ref = new Cloud().getPicsOfCompany(Fanpage.companytype, Fanpage.companyid);
        storage = new Cloudfiles().getCompanyPics(Fanpage.companyid);
        ref.addListenerForSingleValueEvent(this);


        return v;
    }


    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        for(DataSnapshot d : dataSnapshot.getChildren()){

            String path = d.getValue().toString();
            storage.child(path).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    pics.add(uri);
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
