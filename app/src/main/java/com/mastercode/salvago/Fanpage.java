package com.mastercode.salvago;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;
import com.mastercode.salvago.adapters.Fanpage_tabs;
import com.mastercode.salvago.database.Cloud;
import com.mastercode.salvago.database.Cloudfiles;
import com.mastercode.salvago.tools.AppNavigation;
import com.mastercode.salvago.tools.Statictools;

public class Fanpage extends AppCompatActivity implements View.OnClickListener{

    Context ctx;
    ViewPager pager;
    Toolbar toolbar;
    ImageView banner;
    TabLayout tablayout;
    FloatingActionButton fab;
    public static String companyid = "";
    public static String companytype = "";
    StorageReference storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fanpage);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        newVisit();
    }

    public void init(){
        companyid = getIntent().getExtras().getString("id");
        companytype = getIntent().getExtras().getString("type");
        setTitle(companyid);
        ctx = this;

        banner = findViewById(R.id.companybanner);
        tablayout = findViewById(R.id.tablayout);
        pager = findViewById(R.id.viewpager);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        pager.setAdapter(new Fanpage_tabs(getSupportFragmentManager()));
        tablayout.setupWithViewPager(pager);

        /*SETUP BANNER*/
        storage = new Cloudfiles().getCompanyBanner(companyid + ".jpg");
        storage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(ctx).load(uri).into(banner);
            }
        });
    }


    public void newVisit(){
        DatabaseReference ref = new Cloud().getVisitsOfCompany(companytype,companyid).child(Statictools.getSimpleDate()).push();
        ref.setValue(true);
    }


    @Override
    public void onClick(View v) {
        AppNavigation.goMap(this,1,-1);
    }
}
