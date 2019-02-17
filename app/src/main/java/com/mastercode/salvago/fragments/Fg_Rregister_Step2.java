package com.mastercode.salvago.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mastercode.salvago.R;
import com.mastercode.salvago.Register;
import com.mastercode.salvago.database.Cloud;
import com.mastercode.salvago.database.Localbase;
import com.mastercode.salvago.tools.Prefabs;

import java.util.ArrayList;
import java.util.List;

public class Fg_Rregister_Step2 extends Fragment {

    TextView tvError;
    EditText tvStreet;
    Spinner spinDepa, spinCity;
    FloatingActionButton fab;
    List<String> cities;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_register_step2, container,false);
        return init(v);
    }

    public View init(View v){

        //Lista vacia para ciudades
        cities = new ArrayList<>();
        cities.add(0, "Primero selecciona un departamento");
        spinCity = v.findViewById(R.id.input_city);

        //LLenado de Spinner de departamentos
        spinDepa = v.findViewById(R.id.input_departament);
        List<String> departamentos = new ArrayList<>(new Localbase().SalvadorDepartaments().values());
        departamentos.add(0, "Seleccione un departamento");
        spinDepa = Prefabs.setupSpinDesing1(getContext(), spinDepa, departamentos);
        spinDepa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0){
                    UpdateCities(position);
                }else{
                    UpdateVoidCities();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinCity = Prefabs.setupSpinDesing1(getContext(), spinCity, cities);

        tvStreet = v.findViewById(R.id.input_street);
        tvError = v.findViewById(R.id.step_two_error);
        fab = v.findViewById(R.id.fabStep2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isOk();
            }
        });

        return v;
    }

    private void UpdateCities(int depa){
        cities.clear();
        cities = new ArrayList<>(new Localbase().getCitiesByDepartament(depa).values());
        cities.add(0, "Selecciona una ciudad");
        spinCity = Prefabs.setupSpinDesing1(getContext(), spinCity, cities);
    }

    private void UpdateVoidCities(){
        cities.clear();
        cities.add(0, "Primero selecciona un departamento");
        spinCity = Prefabs.setupSpinDesing1(getContext(), spinCity, cities);
    }


    public void isOk(){

        if(spinDepa.getSelectedItemPosition() == 0){
            showError("Selecciona un departamento");
            return;
        }

        if(spinCity.getSelectedItemPosition() == 0){
            showError("Selecciona una ciudad");
            return;
        }

        if (tvStreet.getText().length() < 5){
            showError("Escribe una calle o avenida");
            return;
        }

        Register.nextStep();
    }

    private void showError(String a){
        tvError.setVisibility(View.VISIBLE);
        tvError.setText(a);
    }

}
