package com.mastercode.salvago.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mastercode.salvago.R;
import com.mastercode.salvago.Register;
import com.mastercode.salvago.database.Cloud;
import com.mastercode.salvago.database.Localbase;
import com.mastercode.salvago.tools.Prefabs;

import java.util.List;

public class Fg_Rregister_Step1 extends Fragment implements ValueEventListener {

    TextView tvName, tvDescript, tvError;
    FloatingActionButton fab;
    DatabaseReference ref;
    Spinner spinType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_register_step1, container,false);
        return init(v);
    }

    public View init(View v){

        ref = new Cloud().getSearch();

        fab = v.findViewById(R.id.fabStep1);
        tvError = v.findViewById(R.id.step_one_error);
        tvName = v.findViewById(R.id.input_name_company);
        spinType = v.findViewById(R.id.input_company_type);
        tvDescript = v.findViewById(R.id.input_descript_company);

        List<String> types = new Localbase().CompanyType();
        types.add(0, "Tipo de negocio");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),R.layout.view_white_textview, types);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinType.setAdapter(adapter);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestName();
            }
        });

        return v;
    }

    private void TestName(){
        if(tvName.getText().length() < 1){
            showError("Ingrese el nombre de su compañia o negocio");
            return;
        }
        ref.addListenerForSingleValueEvent(this);
    }

    public void isOk(){
        if(tvDescript.getText().toString().length() < 10){
            showError("Describa su compañia con 10 o mas letras");
            return;
        }

        if(spinType.getSelectedItemPosition() == 0){
            showError("Seleccione una categoria");
            return;
        }

        Register.nextStep();
    }

    private void showError(String a){
        tvError.setVisibility(View.VISIBLE);
        tvError.setText(a);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        String inputName = "@" + tvName.getText().toString().toLowerCase().replace(" ", "");
        Log.e("Register", inputName);
        boolean ok = true;

        for(DataSnapshot d: dataSnapshot.getChildren()){
            if(d.getKey().equals(inputName)){
                ok = false;
                showError("Nombre de compañia ya registrado");
                return;
            }
        }

        if(ok) isOk();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
