package com.mastercode.salvago;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.mastercode.salvago.fragments.Fg_Dashboard_Catalog;
import com.mastercode.salvago.fragments.Fg_Dashboard_Information;
import com.mastercode.salvago.fragments.Fg_Dashboard_Rating;
import com.mastercode.salvago.fragments.Fg_Dashboard_Tags;
import com.mastercode.salvago.fragments.Fg_Dashboard_Visits;

public class Dashcontent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashcontent);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        statusbarcolor();

        //Get parameter
        int index = getIntent().getIntExtra("view", 0);
        changeFragment(index);
    }

    public void statusbarcolor(){
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.dashboardhead));
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
            case 4:
                fg = new Fg_Dashboard_Catalog();
                break;
            case 6:
                fg = new Fg_Dashboard_Rating();
                break;
            case 7:
                fg = new Fg_Dashboard_Visits();
                break;
            default:
                fg = new Fragment();
                break;
        }

        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.multicontent,fg);
        trans.commit();
    }



}
