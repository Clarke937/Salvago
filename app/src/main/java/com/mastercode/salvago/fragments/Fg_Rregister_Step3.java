package com.mastercode.salvago.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.mastercode.salvago.R;
import com.mastercode.salvago.Register;
import com.mastercode.salvago.database.Localbase;
import com.mastercode.salvago.tools.Prefabs;

import java.util.ArrayList;
import java.util.List;

public class Fg_Rregister_Step3 extends Fragment {

    TextView tvError;
    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_register_step3, container,false);
        return init(v);
    }

    public View init(View v){



        return v;
    }



    public void isOk(){


        Register.nextStep();
    }

    private void showError(String a){
        tvError.setVisibility(View.VISIBLE);
        tvError.setText(a);
    }

}
