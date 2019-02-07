package com.mastercode.salvago.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mastercode.salvago.R;
import com.mastercode.salvago.models.ItemOfList;

import java.util.List;

public class Fanpage_info extends BaseAdapter {

    private List<ItemOfList> items;
    private Context ctx;

    public Fanpage_info(Context c, List<ItemOfList> i){
        items = i;
        ctx = c;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(ctx).inflate(R.layout.view_item,parent,false);
        }

        ItemOfList item = items.get(position);
        TextView header = convertView.findViewById(R.id.header);
        TextView text = convertView.findViewById(R.id.content);
        ImageView icon = convertView.findViewById(R.id.icon);

        header.setText(item.header);
        text.setText(item.text);
        icon.setImageDrawable(ctx.getDrawable(item.icon));

        return convertView;
    }
}
