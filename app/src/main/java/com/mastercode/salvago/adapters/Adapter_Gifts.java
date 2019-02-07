package com.mastercode.salvago.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.mastercode.salvago.R;
import com.mastercode.salvago.models.Giftcard;

import java.util.List;

public class Adapter_Gifts extends BaseAdapter implements View.OnClickListener{

    private List<Giftcard> cards;
    private Context ctx;

    public Adapter_Gifts(List<Giftcard> cards, Context ctx) {
        this.cards = cards;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public Object getItem(int position) {
        return cards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Giftcard c = cards.get(position);
        if(convertView == null){
            convertView = LayoutInflater.from(ctx).inflate(R.layout.view_giftcard,parent,false);
        }

        TextView tv_title = convertView.findViewById(R.id.promotitle);
        TextView tv_desc = convertView.findViewById(R.id.shortdescription);

        tv_title.setText(c.title);
        tv_desc.setText(c.shortDescript);
        convertView.setOnClickListener(this);

        return convertView;
    }


    @Override
    public void onClick(View v) {
        Toast.makeText(ctx,"Pulsed",Toast.LENGTH_SHORT).show();
    }
}
