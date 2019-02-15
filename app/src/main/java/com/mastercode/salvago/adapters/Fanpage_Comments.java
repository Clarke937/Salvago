package com.mastercode.salvago.adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mastercode.salvago.R;
import com.mastercode.salvago.models.Opinion;

import java.util.List;

public class Fanpage_Comments extends BaseAdapter {

    private Context ctx;
    private List<Opinion> opiniones;

    public Fanpage_Comments(Context ctx, List<Opinion> opiniones) {
        this.ctx = ctx;
        this.opiniones = opiniones;
    }

    @Override
    public int getCount() {
        return opiniones.size();
    }

    @Override
    public Object getItem(int position) {
        return opiniones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Opinion op = opiniones.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(ctx).inflate(R.layout.view_comment, parent,false);
        }

        ImageView imgv = convertView.findViewById(R.id.icon);
        TextView tvDate = convertView.findViewById(R.id.header);
        TextView tvComment = convertView.findViewById(R.id.content);

        imgv.setImageResource(getEmoji(op.points));

        tvComment.setText(op.comment);
        tvDate.setText(op.date);
        return convertView;
    }

    private int getEmoji(int val){
        switch (val){
            case 1:
                return R.drawable.ic_emoji_angry;
            case 2:
                return R.drawable.ic_emoji_sad;
            case 3:
                return R.drawable.ic_emoji_confused;
            case 4:
                return R.drawable.ic_emoji_happy;
            case 5:
                return R.drawable.ic_emoji_love;
            default:
                return R.drawable.ic_emoji_happy;
        }
    }


}
