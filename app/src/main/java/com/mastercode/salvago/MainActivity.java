package com.mastercode.salvago;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.data.ApnSetting;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

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

    public void init(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                go();
            }
        },3000);
    }

    public void go(){

        FirebaseOptions.Builder build = new FirebaseOptions.Builder()
                .setApplicationId("1:490257703475:android:d10fbbdf33f1217b")
                .setApiKey("AAAAciWi4jM:APA91bFmX6gz25UwkWuRvTdYHpLII5BXAXhMzS21qlA7aADhlG-rAcp-GAtDtv6iW4ScbsdZQVLO6cX1noZcWYS50YD4h4a70uGaDaocUK9Cf6gQNBDNVJgWX-NYCqQI1MMjuGWddd3b")
                .setDatabaseUrl("https://salva-go.firebaseio.com/")
                .setProjectId("salva-go")
                .setStorageBucket("salva-go.appspot.com")
                .setGcmSenderId("490257703475");

        if(MySession.fire_builder == null){
            MySession.fire_builder = build;
            FirebaseApp.initializeApp(this,build.build(),"secondary");
        }

        if(Statictools.TestConexion(this)){
            AppNavigation.goHome(this);
        }
    }
}
