package com.mastercode.salvago.models;
import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;
import java.util.List;

public class Company {

    public int city;
    public Uri banner;
    public String twitter;
    public String website;
    public int departament;
    public double maxPrice;
    public double minPrice;
    public String facebook;
    public String instagram;
    public String companyname;
    public String descripcion;
    public List<Integer> telephones;
    public List<LatLng> coordinates;

    public Company(){

    }

    public Company(String companyname, int city) {
        this.companyname = companyname;
        this.city = city;
    }
}
