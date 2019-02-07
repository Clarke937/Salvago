package com.mastercode.salvago.database;

import android.os.StrictMode;
import android.util.Log;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Server {

    Connection conn;

    public Server(){
        StrictMode.ThreadPolicy police = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(police);

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.56.1/instance=MSSQLSERVER2014;databaseName=Amadeus;integratedSecurity=true;user=RETANA;password=");
        }catch (Exception e){
            Log.e("SERVER",e.getMessage());
        }

    }


    public void ReadData(){
        Statement st;
        try{

            st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select animename from Anime");
            String msj = "";

            while(rs.next()){
                msj += rs.getString(0) + " / ";
            }

            Log.e("MY_DATA",msj);

        }catch (Exception e){

        }
    }

}
