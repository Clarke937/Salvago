package com.mastercode.salvago.tools;

import android.content.Context;
import android.graphics.Color;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;
import com.mastercode.salvago.R;

import java.util.ArrayList;
import java.util.List;

public class Prefabs {


    public static Spinner setupSpinDesing1(Context ctx, Spinner spin, List<String> data){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ctx, R.layout.view_white_textview, data);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spin.setAdapter(adapter);
        return spin;
    }

    public MaterialStyledDialog.Builder ShowInternetAlert(Context ctx){
        MaterialStyledDialog.Builder diag = new MaterialStyledDialog.Builder(ctx);
        diag.setTitle("Opps! No hay conexion");
        diag.setDescription("Revisa tu conexion de internet");
        diag.setStyle(Style.HEADER_WITH_ICON);
        diag.setHeaderColor(R.color.colorAccent);
        diag.setIcon(R.drawable.ic_wifi_error);
        diag.setPositiveText("Reintentar");
        diag.setNegativeText("Cancelar");
        return diag;
    }

    public List<Integer> DashboardColors(){
        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#F44336"));
        colors.add(Color.parseColor("#E91E63"));
        colors.add(Color.parseColor("#9C27B0"));
        colors.add(Color.parseColor("#673AB7"));
        colors.add(Color.parseColor("#3F51B5"));
        colors.add(Color.parseColor("#2196F3"));
        colors.add(Color.parseColor("#00BCD4"));
        colors.add(Color.parseColor("#009688"));
        colors.add(Color.parseColor("#4CAF50"));
        colors.add(Color.parseColor("#03A9F4"));
        return colors;
    }


}
