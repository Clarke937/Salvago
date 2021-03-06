package com.mastercode.salvago.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mastercode.salvago.R;

import java.util.List;

public class Fanpage_Pics extends BaseAdapter implements View.OnClickListener {

    private Context ctx;
    private List<Uri> pics;

    public Fanpage_Pics(Context ctx, List<Uri> pics) {
        this.ctx = ctx;
        this.pics = pics;
    }

    @Override
    public int getCount() {
        return pics.size();
    }

    @Override
    public Object getItem(int position) {
        return pics.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Uri url = pics.get(position);
        if(convertView == null){
            convertView = LayoutInflater.from(ctx).inflate(R.layout.view_picture,parent,false);
        }
        ImageView img = convertView.findViewById(R.id.companyimage);
        Glide.with(ctx).load(url).into(img);

        convertView.setTag(url);
        convertView.setOnClickListener(this);


        return convertView;
    }


    @Override
    public void onClick(View v) {
        Uri uri = (Uri) v.getTag();
        final AlertDialog.Builder build = new AlertDialog.Builder(ctx);
        View vi = LayoutInflater.from(ctx).inflate(R.layout.view_picdialog,null,false);
        ImageView img = vi.findViewById(R.id.picture);
        ImageButton close = vi.findViewById(R.id.close_pic);

        final AlertDialog alert = build.create();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.dismiss();
            }
        });


        Glide.with(ctx).load(uri).into(img);
        alert.setView(vi);
        alert.show();
    }
}
