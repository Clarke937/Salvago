package com.mastercode.salvago.adapters;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mastercode.salvago.R;
import java.util.List;

public class Adapter_Dashboard_Pics extends BaseAdapter {

    private Context ctx;
    private List<Uri> urls;

    public Adapter_Dashboard_Pics(Context ctx, List<Uri> u) {
        this.ctx = ctx;
        this.urls = u;
        Log.e("PHOTO","Constructor");
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public Object getItem(int position) {
        return urls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /*public void addItem(Uri uri){
        this.urls.add(uri);
        Log.e("PHOTO-SIZE", this.urls.size()+"");
        this.notifyDataSetChanged();
    }*/

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Uri u = urls.get(position);
        Log.e("PHOTO POSITION:",position + "");

        if(convertView == null){
            convertView = LayoutInflater.from(ctx).inflate(R.layout.view_pic, parent,false);
        }
        ImageView iv = convertView.findViewById(R.id.dash_pic);
        Glide.with(ctx).load(u).into(iv);



        return convertView;
    }
}
