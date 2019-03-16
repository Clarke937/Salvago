package com.mastercode.salvago.tools;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.maps.android.SphericalUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Statictools {

    public static String getSimpleDate(){
        String fecha = "";
        Calendar cal = Calendar.getInstance();
        fecha += cal.get(Calendar.DAY_OF_MONTH) + "-";
        fecha += (cal.get(Calendar.MONTH) + 1) + "-";
        fecha += cal.get(Calendar.YEAR);
        Log.e("Tiempo", cal.getTimeInMillis() + "");
        return fecha;
    }

    public static boolean isEmailValid(String mail){
        boolean ok = true;
        try {
            InternetAddress ia = new InternetAddress(mail);
            ia.validate();
        }catch (AddressException e){
            ok = false;
        }
        return ok;
    }

    public static boolean TestConexion(final Context ctx){
        ConnectivityManager man = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = man.getActiveNetworkInfo();

        boolean conexion = false;

        if((net != null) && (net.isConnected())){
            conexion = true;
        }else{
            MaterialStyledDialog.Builder diag = new Prefabs().ShowInternetAlert(ctx);
            diag.onPositive(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    AppCompatActivity a = (AppCompatActivity) ctx;
                    dialog.dismiss();
                    a.finish();
                    ctx.startActivity(a.getIntent());
                }
            });
            diag.build().show();
        }
        return conexion;
    }


    public static String MilisToSimpleDate(long milis){
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(milis);
        String builder = "";
        builder += cal.get(Calendar.DAY_OF_MONTH) + "-";
        builder += (cal.get(Calendar.MONTH) + 1) + "-";
        builder += cal.get(Calendar.YEAR);
        return builder;
    }


    public static boolean isToday(long milis){
        String today = getSimpleDate();
        String date = MilisToSimpleDate(milis);
        return (today.equals(date)) ? true : false;
    }

    public static boolean isTomorrow(long milis){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        String tomorrow = MilisToSimpleDate(cal.getTimeInMillis());
        String date = MilisToSimpleDate(milis);
        return (tomorrow.equals(date)) ? true : false;
    }

    public static boolean DateExpired(long milis){

        Calendar calHoy = Calendar.getInstance();
        Calendar calAny = Calendar.getInstance();
        calAny.setTimeInMillis(milis);

        int DayHoy = calHoy.get(Calendar.DAY_OF_MONTH);
        int MesHoy = calHoy.get(Calendar.MONTH);
        int YearHoy = calHoy.get(Calendar.YEAR);

        int DayAny = calAny.get(Calendar.YEAR);
        int MesAny = calAny.get(Calendar.MONTH);
        int YearAny = calAny.get(Calendar.DAY_OF_MONTH);

        if(YearAny > DayHoy){
            return true;
        }else if(YearAny == YearHoy){
            if(MesAny > MesHoy){
                return true;
            }else if (MesAny == MesHoy){
                if(DayAny > DayHoy){
                    return true;
                }
            }
        }
        return false;
    }


    public static double MtsToKms(double mts){
        return mts / 1000;
    }

    public static double getMtsOfMostClose(DataSnapshot d){
        double mts = 0;
        List<Double> distancias = new ArrayList<>();

        for (DataSnapshot da: d.getChildren()) {
            double lat = Double.parseDouble(da.child("lat").getValue().toString());
            double lon = Double.parseDouble(da.child("lon").getValue().toString());

            LatLng coor = new LatLng(lat, lon);
            if(coor == null) Log.e("Sort", "Coor - Null");
            if(MySession.location == null) Log.e("Sort", "Location - Null");
            double distancia = SphericalUtil.computeDistanceBetween(coor, MySession.location);
            distancias.add(distancia);
        }
        Collections.sort(distancias);
        return Math.round(distancias.get(0));
    }



}
