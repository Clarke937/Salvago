package com.mastercode.salvago;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.GridView;

import com.mastercode.salvago.adapters.Adapter_Dashboard_Menu;
import com.mastercode.salvago.models.Menuoption;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {

    GridView list;
    Adapter_Dashboard_Menu adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
    }

    public void init(){
        List<Menuoption> menu = new ArrayList<>();

        menu.add(new Menuoption(R.drawable.ic_edit,"Informacion General"));
        menu.add(new Menuoption(R.drawable.ic_catalog,"Menu de Precios"));
        menu.add(new Menuoption(R.drawable.ic_star,"Promociones"));
        menu.add(new Menuoption(R.drawable.ic_camera,"Fotografias"));
        menu.add(new Menuoption(R.drawable.ic_map_pin,"Ubicaciones"));
        menu.add(new Menuoption(R.drawable.ic_quote,"Calificacion y Comentarios"));
        menu.add(new Menuoption(R.drawable.ic_eye,"Contador de visitas"));
        menu.add(new Menuoption(R.drawable.ic_payments,"Suscripcion Mensual"));


        adapter = new Adapter_Dashboard_Menu(menu, this);
        list = findViewById(R.id.dashmenu);
        list.setAdapter(adapter);
    }


}
