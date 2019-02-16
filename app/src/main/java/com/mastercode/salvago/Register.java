package com.mastercode.salvago;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.mastercode.salvago.customcontrol.Custom_Viewpager;
import com.mastercode.salvago.fragments.Fg_Rregister_Step1;
import com.mastercode.salvago.fragments.Fg_Rregister_Step2;
import com.mastercode.salvago.fragments.Fg_Rregister_Step3;
import com.mastercode.salvago.fragments.Fg_Rregister_Step4;
import com.mastercode.salvago.fragments.Fg_Rregister_Step5;
import com.mastercode.salvago.models.NewCompany;
import com.mastercode.salvago.tools.AppNavigation;
import com.mastercode.salvago.tools.MySession;

public class Register extends AppCompatActivity{

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private static Custom_Viewpager mViewPager;
    Fragment currentFg;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.disableScroll(false);
        mViewPager.invalidate();

        MySession.newcompany = new NewCompany();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }else if(id == android.R.id.home){
            backStep();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static void nextStep(){
        int i = mViewPager.getCurrentItem();
        switch (i){
            case 0:
                mViewPager.setCurrentItem(1);
                break;
            case 1:
                mViewPager.setCurrentItem(2);
                break;
            case 2:
                mViewPager.setCurrentItem(3);
                break;
            case 3:
                mViewPager.setCurrentItem(4);
                break;
        }
    }

    public void backStep(){
        int i = mViewPager.getCurrentItem();
        switch (i){
            case 4:
                mViewPager.setCurrentItem(3);
                break;
            case 3:
                mViewPager.setCurrentItem(2);
                break;
            case 2:
                mViewPager.setCurrentItem(1);
                break;
            case 1:
                mViewPager.setCurrentItem(0);
                break;
            case 0:
                MySession.newcompany = null;
                AppNavigation.goHome(this);
                break;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            currentFg = null;

            switch (position){
                case 0:
                    currentFg = new Fg_Rregister_Step1();
                    break;
                case 1:
                    currentFg = new Fg_Rregister_Step2();
                    break;
                case 2:
                    currentFg = new Fg_Rregister_Step3();
                    break;
                case 3:
                    currentFg = new Fg_Rregister_Step4();
                    break;
                case 4:
                    currentFg = new Fg_Rregister_Step5();
                    break;
            }
            return currentFg;
        }

        @Override
        public int getCount() {
            return 5;
        }
    }
}
