package com.mastercode.salvago.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mastercode.salvago.R;
import com.mastercode.salvago.models.Company;
import com.mastercode.salvago.tools.AppNavigation;

import java.util.List;

public class Home_Companies extends RecyclerView.Adapter<Home_Companies.VwHolder> {

    private Context ctx;
    private List<Company> companies;

    public Home_Companies(Context c, List<Company> com){
        this.ctx = c;
        this.companies = com;
    }

    @NonNull
    @Override
    public VwHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.view_companycard_1,viewGroup,false);
        return new VwHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VwHolder vwHolder, int i) {
        final Company com = companies.get(i);
        vwHolder.tvName.setText(com.companyname);

        if(!com.premium) vwHolder.tvName.setCompoundDrawables(null, null, null, null);

        vwHolder.tvDescript.setText(com.descripcion);
        vwHolder.tvPhone.setText(com.proximity + " mts");

        if(com.banner != null){
            Glide.with(ctx).load(com.banner).into(vwHolder.image);
        }

        vwHolder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppNavigation.goFanpage(com.companytype,com.id,com.companyname,ctx);
            }
        });
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    public class VwHolder extends RecyclerView.ViewHolder{

        TextView tvName, tvPhone, tvDescript;
        CardView card;
        ImageView image;

        public VwHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.banner);
            tvPhone = itemView.findViewById(R.id.phone);
            tvName = itemView.findViewById(R.id.companyname);
            tvDescript = itemView.findViewById(R.id.slogan);
            card = itemView.findViewById(R.id.card);
        }
    }




}
