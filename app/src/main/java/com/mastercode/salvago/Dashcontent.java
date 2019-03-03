package com.mastercode.salvago;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mastercode.salvago.fragments.Fg_Dashboard_Information;
import com.mastercode.salvago.fragments.Fg_Dashboard_Tags;

public class Dashcontent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashcontent);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Get parameter
        int index = getIntent().getIntExtra("view", 0);
        changeFragment(index);
    }

    public void changeFragment(int i){
        Fragment fg = null;

        switch (i){
            case 0:
                fg = new Fg_Dashboard_Information();
                break;
            case 3:
                fg = new Fg_Dashboard_Tags();
                break;
        }

        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.multicontent,fg);
        trans.commit();
    }



}
