package com.mastercode.salvago.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.mastercode.salvago.R;
import com.mastercode.salvago.Register;
import com.mastercode.salvago.tools.AppSecurity;
import com.mastercode.salvago.tools.MySession;
import com.mastercode.salvago.tools.Statictools;

public class Fg_Rregister_Step5 extends Fragment implements View.OnClickListener{

    TextView tvError, tvCard, tvOwner, tvCVC, tvMes, tvYear;
    RadioButton rbPlan1, rbPlan2, rbPlan3;
    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_register_step5, container,false);
        return init(v);
    }

    public View init(View v){

        tvOwner = v.findViewById(R.id.input_cardowner);
        tvCard = v.findViewById(R.id.input_cardnums);
        tvMes = v.findViewById(R.id.input_exp_month);
        tvYear = v.findViewById(R.id.input_exp_year);
        tvCVC = v.findViewById(R.id.input_cardcvc);

        rbPlan1 = v.findViewById(R.id.rbPlan1);
        rbPlan2 = v.findViewById(R.id.rbPlan2);
        rbPlan3 = v.findViewById(R.id.rbPlan3);

        return v;
    }

    @Override
    public void onClick(View v) {

    }

    public void isOk(){


    }

    private void showError(String a){
        tvError.setVisibility(View.VISIBLE);
        tvError.setText(a);
    }
}
