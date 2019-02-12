package com.mastercode.salvago.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mastercode.salvago.Fanpage;
import com.mastercode.salvago.R;
import com.mastercode.salvago.adapters.Fanpage_Locations;
import com.mastercode.salvago.database.Cloud;
import com.mastercode.salvago.models.Location;

import java.util.ArrayList;
import java.util.List;

public class Fg_Fanpage_Locations extends Fragment implements ValueEventListener {

    List<Location> locations;
    ListView listview;
    DatabaseReference ref;
    Fanpage_Locations adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_fanpageinfo, container, false);
        return init(v);
    }

    public View init(View v){
        locations = new ArrayList<>();
        adapter = new Fanpage_Locations(this.getContext(),locations);
        listview = v.findViewById(R.id.list);
        listview.setAdapter(adapter);

        ref = new Cloud().getLocationsOfCompany(Fanpage.companytype, Fanpage.companyid);
        ref.addListenerForSingleValueEvent(this);
        return v;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        locations.clear();
        for(DataSnapshot d : dataSnapshot.getChildren()) {
            Location loca = new Location();
            loca.city = Integer.parseInt(d.child("city").getValue().toString());
            loca.departament = Integer.parseInt(d.child("departament").getValue().toString());
            loca.latitud = Double.parseDouble(d.child("lat").getValue().toString());
            loca.longitud = Double.parseDouble(d.child("lon").getValue().toString());
            loca.street = d.child("street").getValue().toString();
            locations.add(loca);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
