package com.mastercode.salvago.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View;
import android.widget.TextView;

import com.mastercode.salvago.R;
import com.mastercode.salvago.database.Localbase;
import com.mastercode.salvago.models.Location;
import com.mastercode.salvago.tools.AppNavigation;

import java.util.List;

public class Fanpage_Locations extends BaseAdapter implements View.OnClickListener {

    private Context ctx;
    List<Location> locations;

    public Fanpage_Locations(Context ctx, List<Location> locations) {
        this.ctx = ctx;
        this.locations = locations;
    }

    @Override
    public int getCount() {
        return locations.size();
    }

    @Override
    public Object getItem(int position) {
        return locations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Location loca = locations.get(position);
        if(convertView == null){
            convertView = LayoutInflater.from(ctx).inflate(R.layout.view_item_location,parent,false);
        }

        boolean isMultiple = locations.size() > 1 ? true : false;
        String city = new Localbase().SalvadorCities().get(loca.city);
        String dept = new Localbase().SalvadorDepartaments().get(loca.departament);

        TextView tvDirection = convertView.findViewById(R.id.content);
        TextView tvHeader = convertView.findViewById(R.id.header);

        tvDirection.setText(city + ", " + dept + ", " + loca.street);

        if(isMultiple){
            tvHeader.setText("Sucursal " + (position + 1));
        }else{
            tvHeader.setText("Unica sucursal");
        }

        convertView.setTag(loca);
        convertView.setOnClickListener(this);
        return convertView;
    }


    @Override
    public void onClick(View v) {
        Location loca = (Location) v.getTag();
        AppNavigation.goMap(ctx, loca.latitud, loca.longitud);
    }
}
