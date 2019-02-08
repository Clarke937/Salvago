package com.mastercode.salvago.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mastercode.salvago.R;
import com.mastercode.salvago.models.Product;

import java.util.List;

public class Fanpage_menu extends BaseAdapter {

    private Context ctx;
    private List<Product> products;

    public Fanpage_menu(Context ctx, List<Product> products) {
        this.ctx = ctx;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Product p = products.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(ctx).inflate(R.layout.view_item_product,parent,false);
        }

        ImageView options;
        TextView tvTitle = convertView.findViewById(R.id.productTitle);
        TextView tvDescription = convertView.findViewById(R.id.productDescription);
        TextView tvPrice = convertView.findViewById(R.id.productPrice);

        tvTitle.setText(p.title);
        tvDescription.setText(p.description);
        tvPrice.setText("$" + p.price);
        Log.e("PRODUCT",p.description);

        return convertView;
    }
}
