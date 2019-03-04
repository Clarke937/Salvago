package com.mastercode.salvago.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mastercode.salvago.R;
import com.mastercode.salvago.models.Product;

import org.w3c.dom.Text;

import java.util.List;

public class Rcv_Menu_Adapter extends RecyclerView.Adapter<Rcv_Menu_Adapter.VHolder> {

    Context ctx;
    List<Product> products;

    public Rcv_Menu_Adapter(Context ctx, List<Product> products) {
        this.ctx = ctx;
        this.products = products;
    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.view_item_product, viewGroup,false);
        v.setPadding(0, 24, 8, 24);
        return new VHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VHolder vHolder, int i) {
        Product p = products.get(i);
        vHolder.tvTitle.setText(p.title);
        vHolder.tvDescription.setText(p.description);
        vHolder.tvPrice.setText("$"+p.price);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class VHolder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvPrice, tvDescription;
        public VHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.productTitle);
            tvPrice = itemView.findViewById(R.id.productPrice);
            tvDescription = itemView.findViewById(R.id.productDescription);
        }
    }
}
