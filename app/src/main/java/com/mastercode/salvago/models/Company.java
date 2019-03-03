package com.mastercode.salvago.models;
import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;

import java.util.Comparator;
import java.util.List;

public class Company implements Comparable<Company>{

    public int city;
    public String id;
    public Uri banner;
    public String twitter;
    public String website;
    public int departament;
    public String facebook;
    public String instagram;
    public String companyname;
    public String descripcion;
    public List<Integer> telephones;
    public List<LatLng> coordinates;
    public String telephone;
    public boolean premium;
    public double proximity;

    public Company(){

    }

    public Company(String companyname, int city) {
        this.companyname = companyname;
        this.city = city;
    }

    @Override
    public int compareTo(Company o) {
        return this.proximity > o.proximity ? 1 : this.proximity < o.proximity ? -1 : 0;
    }
}