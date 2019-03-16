package com.mastercode.salvago.tools;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseUser;
import com.mastercode.salvago.models.Busqueda;
import com.mastercode.salvago.models.NewCompany;

public class MySession {

    public static FirebaseUser fbuser;
    public static int home_fragment;
    public static NewCompany newcompany;
    public static FirebaseOptions.Builder fire_builder;
    public static Busqueda busqueda;

    public static LatLng location;

}
