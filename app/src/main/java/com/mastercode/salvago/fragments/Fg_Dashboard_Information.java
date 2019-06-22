package com.mastercode.salvago.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mastercode.salvago.R;
import com.mastercode.salvago.database.Cloud;
import com.mastercode.salvago.tools.MySession;

import java.util.List;

public class Fg_Dashboard_Information extends Fragment implements ValueEventListener {

    EditText etTitulo, etTelefono, etDescription;
    DatabaseReference ref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_dashboard_information, container, false);
        return init(v);
    }

    View init(View v){
        etTitulo = v.findViewById(R.id.titulo);
        etTelefono = v.findViewById(R.id.telefono);
        etDescription = v.findViewById(R.id.descripcion);
        etTitulo.setText(MySession.dashcompany.companyname);
        ref = new Cloud().getInfoOfCompany(MySession.dashcompany.companytype,MySession.dashcompany.id);
        ref.addListenerForSingleValueEvent(this);
        return v;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        String description = dataSnapshot.child("description").getValue().toString();
        etDescription.setText(description);

        if(dataSnapshot.child("telephones").exists()){
            String telephones = dataSnapshot.child("telephones").child("0").getValue().toString();
            etTelefono.setText(telephones);
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
