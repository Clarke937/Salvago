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
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mastercode.salvago.R;
import com.mastercode.salvago.adapters.Adapter_Visits_Timeline;
import com.mastercode.salvago.database.Cloud;
import com.mastercode.salvago.models.Visitday;
import com.mastercode.salvago.tools.Statictools;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Fg_Dashboard_Visits extends Fragment implements ValueEventListener {

    RecyclerView rcv;
    DatabaseReference ref;
    List<Visitday> visits;
    Adapter_Visits_Timeline adapter;
    TextView vtotal,vyesterday,vtoday;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_dashboard_visits, container,false);
        return init(v);
    }


    private View init(View v){

        vtotal = v.findViewById(R.id.totalvisits);
        vtoday = v.findViewById(R.id.today);
        vyesterday = v.findViewById(R.id.yesterday);

        visits = new ArrayList<>();
        adapter = new Adapter_Visits_Timeline(getContext(), visits);

        rcv = v.findViewById(R.id.rcvtimeline);
        rcv.setHasFixedSize(true);
        rcv.setLayoutManager(new LinearLayoutManager(getContext()));
        rcv.setAdapter(adapter);

        ref = new Cloud().getVisitsOfCompany("restaurants", "@testcompany2");
        ref.addListenerForSingleValueEvent(this);
        return v;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        long total = 0;
        long today = 0;
        long yesterday = 0;

        for (DataSnapshot d : dataSnapshot.getChildren()) {
            total += d.getChildrenCount();

            if (d.getKey().equals(Statictools.getSimpleDate())) {
                today = d.getChildrenCount();
            }

            if(d.getKey().equals(Statictools.getYesterdaySimpleDate())){
                yesterday = d.getChildrenCount();
            }

            Visitday v = new Visitday();
            v.date = d.getKey();
            v.count = d.getChildrenCount();
            visits.add(v);
        }

        String ftotal = NumberFormat.getNumberInstance(Locale.US).format(total);
        vtotal.setText(ftotal);

        String ftoday = NumberFormat.getNumberInstance(Locale.US).format(today);
        vtoday.setText(ftoday);

        String fyeste = NumberFormat.getNumberInstance(Locale.US).format(yesterday);
        vyesterday.setText(fyeste);

        Collections.reverse(visits);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
