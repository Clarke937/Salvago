package com.mastercode.salvago;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.data.ApnSetting;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.mastercode.salvago.tools.AppNavigation;
import com.mastercode.salvago.tools.MySession;
import com.mastercode.salvago.tools.Prefabs;
import com.mastercode.salvago.tools.Statictools;

public class MainActivity extends AppCompatActivity {

    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        ctx = this;
        init();
    }

    public void init() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                prepare();
            }
        },2000);
    }

    public void prepare(){
        FirebaseOptions.Builder build = new FirebaseOptions.Builder()
                .setApplicationId("1:490257703475:android:d10fbbdf33f1217b")

                .setApiKey("AIzaSyCLKZQnVGsDXEPqiHYB6WfNMHz3HNpL1H0")
                //.setApiKey("AAAAciWi4jM:APA91bFmX6gz25UwkWuRvTdYHpLII5BXAXhMzS21qlA7aADhlG-rAcp-GAtDtv6iW4ScbsdZQVLO6cX1noZcWYS50YD4h4a70uGaDaocUK9Cf6gQNBDNVJgWX-NYCqQI1MMjuGWddd3b")
                .setDatabaseUrl("https://salva-go.firebaseio.com/")

                .setProjectId("salva-go")
                .setStorageBucket("salva-go.appspot.com")
                .setGcmSenderId("490257703475");

        if (MySession.fire_builder == null) {
            MySession.fire_builder = build;
            FirebaseApp.initializeApp(this, build.build(), "secondary");
        }

        if(checkGPSPermission() == PackageManager.PERMISSION_DENIED){
            pedirPermiso();
        }else{
            if(Statictools.TestConexion(this)){
                AppNavigation.goHome(this);
            }
        }
    }

    public void pedirPermiso(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(grantResults.length > 0){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                go();
            }else if(grantResults[0] == PackageManager.PERMISSION_DENIED){
                MaterialStyledDialog.Builder dialog = new MaterialStyledDialog.Builder(this);
                dialog.setTitle("Localizacion bloqueada");
                dialog.setDescription("Es necesario que active la ubicacion para continuar");
                dialog.setStyle(Style.HEADER_WITH_ICON);
                dialog.setHeaderDrawable(R.drawable.ic_map_pin);
                dialog.setPositiveText("Activar GPS");
                dialog.setNegativeText("Salir");
                dialog.onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        pedirPermiso();
                    }
                });
                dialog.onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        MainActivity.this.finish();
                    }
                });

                dialog.build().show();
            }
        }

    }

    //Check GPS Permission
    public int checkGPSPermission(){
        int check = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        return check;
    }

    public void go() {
        if(Statictools.TestConexion(this)){
            AppNavigation.goHome(this);
        }
    }




}
