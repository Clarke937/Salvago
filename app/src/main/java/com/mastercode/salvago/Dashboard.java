package com.mastercode.salvago;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.Toast;

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
        statusbarcolor();
        init();
    }

    public void statusbarcolor(){
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.dashboardhead));
    }

    public void init(){
        List<Menuoption> menu = new ArrayList<>();

        menu.add(new Menuoption(R.drawable.ic_edit_outline,"Informacion General"));
        menu.add(new Menuoption(R.drawable.ic_photo_camera_outline,"Fotografias"));
        menu.add(new Menuoption(R.drawable.ic_address,"Ubicaciones"));
        menu.add(new Menuoption(R.drawable.ic_hastag,"Etiquetas"));
        menu.add(new Menuoption(R.drawable.ic_menu_outline,"Catalogo de Precios"));
        menu.add(new Menuoption(R.drawable.ic_discount,"Promociones"));
        menu.add(new Menuoption(R.drawable.ic_3_stars_outlines,"Evaluacion"));
        menu.add(new Menuoption(R.drawable.ic_binoculars,"Contador de visitas"));
        menu.add(new Menuoption(R.drawable.ic_payments,"Suscripcion Mensual"));

        adapter = new Adapter_Dashboard_Menu(menu, this);
        list = findViewById(R.id.dashmenu);
        list.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
