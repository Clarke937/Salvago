package com.mastercode.salvago.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mastercode.salvago.fragments.Fg_Fanpage_Info;
import com.mastercode.salvago.fragments.Fg_Fanpage_Menu;

public class Fanpage_tabs extends FragmentPagerAdapter {

    public Fanpage_tabs(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {

        switch (pos){
            case 0:
                return new Fg_Fanpage_Info();
            case 1:
                return new Fg_Fanpage_Menu();
            case 2:
                return new Fragment();
            case 3:
                return new Fragment();
            case 4:
                return new Fragment();
            default:
                return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:
                return "INFORMACION";
            case 1:
                return "MENU";
            case 2:
                return "PROMOCIONES";
            case 3:
                return "FOTOS";
            case 4:
                return "OPINIONES";
            default:
                return "";
        }

    }
}
