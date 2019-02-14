package com.mastercode.salvago.tools;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.mastercode.salvago.R;

import java.util.List;

public class Prefabs {


    public static Spinner setupSpinDesing1(Context ctx, Spinner spin, List<String> data){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(ctx, R.layout.view_white_textview, data);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spin.setAdapter(adapter);
        return spin;
    }

}
