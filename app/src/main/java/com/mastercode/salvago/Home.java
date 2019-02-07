package com.mastercode.salvago;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

import com.mastercode.salvago.fragments.Fg_Nav_Hotel;
import com.mastercode.salvago.fragments.Fg_Nav_Promos;
import com.mastercode.salvago.fragments.Fg_Nav_Restaurant;
import com.mastercode.salvago.fragments.Fg_Nav_Services;
import com.mastercode.salvago.fragments.Fg_Nav_Stores;
import com.mastercode.salvago.fragments.Fg_Nav_Tourism;
import com.mastercode.salvago.tools.AppNavigation;
import com.mastercode.salvago.tools.Popups;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    View headerview;
    Context ctx;

    public void initvars(){
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        ctx = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initvars();

        setSupportActionBar(toolbar);
        setTitle("Inicio");

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(headerview.getWindowToken(),0);
                super.onDrawerClosed(drawerView);
            }
        };
        navigationView.setNavigationItemSelectedListener(this);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        headerview = navigationView.getHeaderView(0);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else {
            AlertDialog dialog = new Popups(this).CloseApp(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            dialog.show();
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menu, m);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_giftcard){
            AppNavigation.goGiftcards(this);
        }
        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.nav_promo:

                break;
            case R.id.nav_restaurant:
                ChangeFragment(1);
                break;
            case R.id.nav_turist:
                ChangeFragment(2);
                break;
            case R.id.nav_hotel:
                ChangeFragment(3);
                break;
            case R.id.nav_store:
                ChangeFragment(4);
                break;
            case R.id.nav_service:
                ChangeFragment(5);
                break;
            case R.id.nav_new:
                break;
            case R.id.nav_login:
                AppNavigation.goAccess(this);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void ChangeFragment(int index){
        Fragment frag = null;
        FragmentManager manager = getSupportFragmentManager();

        switch (index){
            case 0:
                frag = new Fg_Nav_Promos();
                break;
            case 1:
                frag = new Fg_Nav_Restaurant();
                break;
            case 2:
                frag = new Fg_Nav_Tourism();
                break;
            case 3:
                frag = new Fg_Nav_Hotel();
                break;
            case 4:
                frag = new Fg_Nav_Stores();
                break;
            case 5:
                frag = new Fg_Nav_Services();
                break;
        }

        manager.beginTransaction().replace(R.id.fgcontainer,frag).commit();
        drawer.closeDrawer(GravityCompat.START);
    }

}
