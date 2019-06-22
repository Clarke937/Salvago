package com.mastercode.salvago.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mastercode.salvago.R;
import com.mastercode.salvago.models.Promo;
import com.mastercode.salvago.tools.Statictools;

import java.util.List;

public class Fanpage_Promos extends BaseAdapter {

    private Context ctx;
    private List<Promo> promos;

    public Fanpage_Promos(Context ctx, List<Promo> promos) {
        this.ctx = ctx;
        this.promos = promos;
    }

    @Override
    public int getCount() {
        return promos.size();
    }

    @Override
    public Object getItem(int position) {
        return promos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Promo p = promos.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(ctx).inflate(R.layout.view_fanpage_promo,parent,false);
        }

        ImageView imgPro = convertView.findViewById(R.id.promo_image);
        TextView tvTitle = convertView.findViewById(R.id.promo_title);
        TextView tvDescr = convertView.findViewById(R.id.promo_description);
        TextView tvPrice = convertView.findViewById(R.id.promo_price);
        TextView tvExpir = convertView.findViewById(R.id.promo_expiration);

        tvTitle.setText(p.title);
        tvDescr.setText(p.description);
        tvPrice.setText("$" + p.price_promo);
        tvExpir.setText("Expira: " + Statictools.MilisToSimpleDate(p.date_fin));
        Glide.with(ctx).load(p.pic).into(imgPro);

        return convertView;
    }
}
