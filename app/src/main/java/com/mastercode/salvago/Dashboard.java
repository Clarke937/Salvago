package com.mastercode.salvago;

import android.os.Bundle;
import android.se.omapi.Session;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mastercode.salvago.adapters.Adapter_Dashboard_Menu;
import com.mastercode.salvago.database.Cloud;
import com.mastercode.salvago.models.Company;
import com.mastercode.salvago.models.Menuoption;
import com.mastercode.salvago.tools.MySession;
import com.mastercode.salvago.tools.Statictools;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity implements ValueEventListener {

    TextView date;
    GridView list;
    TextView company;
    DatabaseReference ref;
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
        date = findViewById(R.id.dash_date);
        company = findViewById(R.id.dash_companyname);
        date.setText(Statictools.getSimpleDate());

        List<Menuoption> menu = new ArrayList<>();
        menu.add(new Menuoption(R.drawable.ic_edit_outline,"Informacion General"));
        menu.add(new Menuoption(R.drawable.ic_photo_camera_outline,"Fotografias"));
        menu.add(new Menuoption(R.drawable.ic_address,"Ubicaciones"));
        menu.add(new Menuoption(R.drawable.ic_hastag,"Etiquetas"));
        menu.add(new Menuoption(R.drawable.ic_menu_outline,"Catalogo de Precios"));
        menu.add(new Menuoption(R.drawable.ic_discount,"Promociones"));
        menu.add(new Menuoption(R.drawable.ic_3_stars_outlines,"Evaluacion"));
        menu.add(new Menuoption(R.drawable.ic_binoculars,"Contador de visitas"));
        menu.add(new Menuoption(R.drawable.ic_network,"Redes Sociales"));
        menu.add(new Menuoption(R.drawable.ic_payments,"Suscripcion Mensual"));

        adapter = new Adapter_Dashboard_Menu(menu, this);
        list = findViewById(R.id.dashmenu);
        list.setAdapter(adapter);

        ref = new Cloud().getSearch();
        ref.addListenerForSingleValueEvent(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        int indexAt = MySession.fbuser.getEmail().indexOf("@");
        String id = MySession.fbuser.getEmail().substring(indexAt,MySession.fbuser.getEmail().indexOf("."));

        for(DataSnapshot d : dataSnapshot.getChildren()){
            if(d.getKey().equals(id)){
                String comid = d.getKey();
                String comcategory = d.child("category").getValue().toString();
                String comtitle = d.child("title").getValue().toString();

                Company com = new Company();
                com.id = comid;
                com.companytype = comcategory;
                com.companyname = comtitle;
                company.setText(comtitle);
                MySession.dashcompany = com;
            }
        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
