package com.mastercode.salvago.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ValueEventListener;
import com.mastercode.salvago.R;
import com.mastercode.salvago.Register;
import com.mastercode.salvago.database.Localbase;
import com.mastercode.salvago.tools.MySession;
import com.mastercode.salvago.tools.Prefabs;

import java.util.ArrayList;
import java.util.List;

public class Fg_Rregister_Step3 extends Fragment implements View.OnClickListener {

    EditText tvFb, tvTw, tvIns, tvWeb;
    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_register_step3, container,false);
        return init(v);
    }

    public View init(View v){
        tvIns = v.findViewById(R.id.input_instagram);
        tvFb = v.findViewById(R.id.input_facebook);
        tvWeb = v.findViewById(R.id.input_webpage);
        tvTw = v.findViewById(R.id.input_twitter);
        fab = v.findViewById(R.id.fabStep3);

        fab.setOnClickListener(this);
        return v;
    }


    @Override
    public void onClick(View v) {
        Log.e("Step3", "IsOk");
        isOk();
    }

    public void isOk(){

        boolean e1 = tvFb.getText().toString().isEmpty();
        boolean e2 = tvTw.getText().toString().isEmpty();
        boolean e3 = tvIns.getText().toString().isEmpty();
        boolean e4 = tvWeb.getText().toString().isEmpty();

        if(!e1) MySession.newcompany.facebook = tvFb.getText().toString();
        if(!e2) MySession.newcompany.twitter = tvTw.getText().toString();
        if(!e3) MySession.newcompany.instagram = tvIns.getText().toString();
        if(!e4) MySession.newcompany.website = tvWeb.getText().toString();

        Register.nextStep();
    }

}
