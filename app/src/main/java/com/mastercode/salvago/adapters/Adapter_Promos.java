package com.mastercode.salvago.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mastercode.salvago.R;
import com.mastercode.salvago.models.Promo;
import com.mastercode.salvago.tools.Statictools;

import java.util.List;

public class Adapter_Promos extends RecyclerView.Adapter<Adapter_Promos.VwHolder> {

    List<Promo> promos;
    Context ctx;

    public Adapter_Promos(List<Promo> promos, Context ctx) {
        this.promos = promos;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public VwHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.view_promo,viewGroup,false);
        return new VwHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VwHolder vwHolder, int i) {
        Promo pro = promos.get(i);
        vwHolder.tvTitle.setText(pro.title);

        boolean hasText = false;

        //Si la fecha de expiracion es hoy
        if(Statictools.isToday(pro.date_fin)) {
            hasText = true;
            vwHolder.tvPrice.setText("Expiracion: Hoy");
        }
        //Si la fecha de expiracion es mañana
        if(Statictools.isTomorrow(pro.date_fin)) {
            hasText = true;
            vwHolder.tvPrice.setText("Expiracion: Mañana");
        }
        //Si la fecha de expiracion es mucho mas en el futuro
        if(!hasText) vwHolder.tvPrice.setText("Expiracion: " + Statictools.MilisToSimpleDate(pro.date_fin));

        vwHolder.tvDesc.setText(pro.description);
        vwHolder.tvCompany.setText(pro.company);
        Glide.with(ctx).load(pro.pic).into(vwHolder.imv);
    }

    @Override
    public int getItemCount() {
        return promos.size();
    }

    public class VwHolder extends RecyclerView.ViewHolder{

        ImageView imv;
        TextView tvTitle, tvDesc, tvPrice, tvCompany;

        public VwHolder(@NonNull View itemView) {
            super(itemView);

            imv = itemView.findViewById(R.id.promoimg);
            tvTitle = itemView.findViewById(R.id.promoTitle);
            tvPrice = itemView.findViewById(R.id.promoPrice);
            tvDesc = itemView.findViewById(R.id.promoDescription);
            tvCompany = itemView.findViewById(R.id.promocompany);
        }
    }

}
