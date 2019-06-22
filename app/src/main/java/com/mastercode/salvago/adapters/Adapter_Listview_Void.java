package com.mastercode.salvago.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mastercode.salvago.R;

public class Adapter_Listview_Void extends BaseAdapter {

    Context ctx;

    public Adapter_Listview_Void(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(ctx).inflate(R.layout.view_void,parent,false);
        return convertView;
    }
}
