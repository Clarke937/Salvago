package com.mastercode.salvago.models;

public class Location {

    public int city;
    public int departament;
    public double latitud;
    public double longitud;
    public String street;

    public Location() {

    }

    public Location(int city, int departament, double latitud, double longitud, String street) {
        this.city = city;
        this.departament = departament;
        this.latitud = latitud;
        this.longitud = longitud;
        this.street = street;
    }
}
