package com.mastercode.salvago;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.mastercode.salvago.adapters.Fanpage_tabs;
import com.mastercode.salvago.tools.AppNavigation;

public class Fanpage extends AppCompatActivity implements View.OnClickListener{

    ViewPager pager;
    Toolbar toolbar;
    TabLayout tablayout;
    FloatingActionButton fab;
    String companyid = "@testcompany";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fanpage);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
    }

    public void init(){
        tablayout = findViewById(R.id.tablayout);
        pager = findViewById(R.id.viewpager);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
        pager.setAdapter(new Fanpage_tabs(getSupportFragmentManager()));
        tablayout.setupWithViewPager(pager);
    }


    @Override
    public void onClick(View v) {
        AppNavigation.goMap(this,1,-1);
    }
}
