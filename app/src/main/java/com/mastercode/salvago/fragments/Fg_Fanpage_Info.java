package com.mastercode.salvago.fragments;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mastercode.salvago.Fanpage;
import com.mastercode.salvago.R;
import com.mastercode.salvago.adapters.Fanpage_info;
import com.mastercode.salvago.database.Cloud;
import com.mastercode.salvago.database.Server;
import com.mastercode.salvago.models.ItemOfList;

import java.util.ArrayList;
import java.util.List;

public class Fg_Fanpage_Info extends Fragment implements ValueEventListener {

    ListView listview;
    List<ItemOfList> items;
    BaseAdapter adapter;
    static Context ctx;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_fanpageinfo, container, false);
        ctx = this.getContext();
        return init(v);
    }

    public View init(View v) {
        listview = v.findViewById(R.id.list);
        items = new ArrayList<>();
        adapter = new Fanpage_info(this.getContext(), items);
        listview.setAdapter(adapter);

        DatabaseReference ref = new Cloud().getRestaurantInfo(Fanpage.companyid);
        ref.addListenerForSingleValueEvent(this);

        return v;
    }


    @Override
    public void onDataChange(@NonNull DataSnapshot d) {

        String descripcion = d.child("description").getValue().toString();
        String title = d.child("title").getValue().toString();
        String website = d.child("website").getValue().toString();
        String fb = d.child("social").child("facebook").getValue().toString();

        /*TELEPHONES*/
        String telephones = "";
        if(d.child("telephones").hasChildren()){
            for (DataSnapshot ds : d.child("telephones").getChildren()){
                telephones += ds.getValue().toString() + "\n";
            }
        }else{
            telephones = d.child("telephones").getValue().toString();
        }

        /*PRICES*/
        String min = d.child("prices").child("min").getValue().toString();
        String max = d.child("prices").child("max").getValue().toString();
        String prices = "$" + min + " - $" + max;

        /*POPULATE ITEMS*/
        items.add(new ItemOfList("Compañia:",title,R.drawable.ic_store));
        items.add(new ItemOfList("Descripcion:",descripcion,R.drawable.ic_quote));
        items.add(new ItemOfList("Rango de Precios:",prices,R.drawable.ic_dollar));
        items.add(new ItemOfList("Numeros Telefonicos:",telephones.trim(),R.drawable.ic_phone));
        items.add(new ItemOfList("Facebook:",fb,R.drawable.ic_facebook));
        items.add(new ItemOfList("Pagina Web:",website,R.drawable.ic_globe));

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    public int getChildInt(DataSnapshot d, String key){
        return Integer.parseInt(d.child(key).getValue().toString());
    }

}
