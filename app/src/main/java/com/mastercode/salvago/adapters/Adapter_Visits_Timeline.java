package com.mastercode.salvago.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mastercode.salvago.R;
import com.mastercode.salvago.models.Visitday;
import com.mastercode.salvago.tools.Statictools;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Adapter_Visits_Timeline extends RecyclerView.Adapter<Adapter_Visits_Timeline.VWHolder>{

    Context ctx;
    List<Visitday> visits;

    public Adapter_Visits_Timeline(Context ctx, List<Visitday> visits) {
        this.ctx = ctx;
        this.visits = visits;
    }

    @NonNull
    @Override
    public VWHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.view_timeline_item, viewGroup,false);
        return new VWHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VWHolder vwHolder, int i) {
        Visitday vd = visits.get(i);

        if(i < visits.size()-1){
            if(visits.get(i+1).count > visits.get(i).count){
                vwHolder.icon.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_trending_down));
            }else if(visits.get(i+1).count == visits.get(i).count){
                vwHolder.icon.setImageDrawable(ctx.getResources().getDrawable(R.drawable.ic_trending_normal));
            }
        }

        int month = Integer.parseInt(vd.date.split("-")[1]);
        String count = NumberFormat.getNumberInstance(Locale.US).format(vd.count);

        vwHolder.tvCount.setText(count);
        vwHolder.tvMoth.setText(Statictools.getMonthAbv(month));
        vwHolder.tvDay.setText(vd.date.split("-")[0]);

    }

    @Override
    public int getItemCount() {
        return visits.size();
    }

    public class VWHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView tvMoth,tvDay,tvCount;
        public VWHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.timeline_icon);
            tvMoth = itemView.findViewById(R.id.timeline_month);
            tvDay = itemView.findViewById(R.id.timeline_day);
            tvCount = itemView.findViewById(R.id.timeline_count);
        }
    }

}
