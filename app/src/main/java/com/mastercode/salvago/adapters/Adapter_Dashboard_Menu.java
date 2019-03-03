package com.mastercode.salvago.adapters;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mastercode.salvago.Dashboard;
import com.mastercode.salvago.R;
import com.mastercode.salvago.models.Menuoption;
import com.mastercode.salvago.tools.AppNavigation;
import com.mastercode.salvago.tools.Prefabs;
import java.util.List;

public class Adapter_Dashboard_Menu extends BaseAdapter implements View.OnClickListener {

    List<Menuoption> opciones;
    Context ctx;

    public Adapter_Dashboard_Menu(List<Menuoption> opciones, Context ctx) {
        this.opciones = opciones;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return opciones.size();
    }

    @Override
    public Object getItem(int position) {
        return opciones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int ic = opciones.get(position).icon;
        String text = opciones.get(position).texto;

        if(convertView == null){
            convertView = LayoutInflater.from(ctx).inflate(R.layout.card_menu_dashboard,parent,false);
        }

        ImageView icon = convertView.findViewById(R.id.dashmenuicon);
        icon.setImageDrawable(ctx.getResources().getDrawable(ic));

        TextView option = convertView.findViewById(R.id.dashmenuop);
        option.setText(text);

        convertView.setTag(position);
        convertView.setOnClickListener(this);


        return convertView;
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        AppNavigation.goDashcontent(ctx, position);
    }
}
