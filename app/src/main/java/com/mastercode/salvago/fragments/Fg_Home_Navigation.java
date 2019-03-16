package com.mastercode.salvago.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.LocationManager;
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

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.maps.android.SphericalUtil;
import com.location.aravind.getlocation.GeoLocator;
import com.mastercode.salvago.R;
import com.mastercode.salvago.adapters.Home_Companies;
import com.mastercode.salvago.database.Cloud;
import com.mastercode.salvago.database.Cloudfiles;
import com.mastercode.salvago.models.Company;
import com.mastercode.salvago.tools.MySession;
import com.mastercode.salvago.tools.Statictools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fg_Home_Navigation extends Fragment implements ValueEventListener {

    int index;
    Query ref;
    Context ctx;
    Home_Companies adapter;
    List<Company> companies;
    StorageReference storage;
    ProgressDialog progress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_recyclerview,container,false);
        index = getArguments().getInt("option");
        ctx = this.getContext();
        return init(v);
    }

    public View init(View v){

        progress = new ProgressDialog(getContext());

        RecyclerView rcv = v.findViewById(R.id.rcv);
        rcv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rcv.setHasFixedSize(true);

        companies = new ArrayList<>();
        String title = "";

        switch (index) {
            case 1:
                adapter = new Home_Companies(this.getContext(),companies);
                ref = new Cloud().getRestaurants();
                title = "Restaurantes";
                break;
            case 2:
                adapter = new Home_Companies(this.getContext(),companies);
                ref = new Cloud().getTourist();
                title = "Turismo";
                break;
            case 3:
                adapter = new Home_Companies(this.getContext(),companies);
                ref = new Cloud().getHotels();
                title = "Hospedajes";
                break;
            case 4:
                adapter = new Home_Companies(this.getContext(),companies);
                ref = new Cloud().getShops();
                title = "Tiendas";
                break;
            case 5:
                adapter = new Home_Companies(this.getContext(),companies);
                ref = new Cloud().getServices();
                title = "Servicios";
                break;
        }

        rcv.setAdapter(adapter);
        getActivity().setTitle(title);
        storage = new Cloudfiles().getBanners();
        getData();
        return v;
    }


    public void getData(){
        companies.clear();
        ref.addListenerForSingleValueEvent(this);

    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        progress.show();

        for(DataSnapshot d : dataSnapshot.getChildren()){

            final Company com = new Company();
            com.id = d.getKey();
            com.companyname = d.child("info").child("title").getValue().toString();
            com.descripcion = d.child("info").child("description").getValue().toString();
            com.premium = Boolean.parseBoolean(d.child("account").child("premium").getValue().toString());
            com.companytype = d.getRef().getKey();
            Log.e("Navi", com.companyname);

            //Obtencion de las coordenadas de todas las sucursales
            if(d.child("locations").exists()){
                com.proximity = Statictools.getMtsOfMostClose(d.child("locations"));
            }
            companies.add(com);
        }

        SortCompanies();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    public void SortCompanies(){

        List<Company> premiums = new ArrayList<>();
        List<Company> normals = new ArrayList<>();

        for (Company co : companies){
            if(co.premium){
                premiums.add(co);
            }else{
                normals.add(co);
            }
        }

        Collections.sort(premiums);
        Collections.sort(normals);

        companies.clear();

        companies.addAll(premiums);
        companies.addAll(normals);
        DownloadPics();
    }

    public void DownloadPics(){
        for(final Company c : companies){
            storage.child(c.id + ".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    c.banner = uri;
                    adapter.notifyDataSetChanged();
                }
            });
        }
        progress.dismiss();
    }

    public void DoSearch(List<Company> companies){
        for (Company com: companies) {

        }
    }


}
