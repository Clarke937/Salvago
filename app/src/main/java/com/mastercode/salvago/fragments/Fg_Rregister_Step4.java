package com.mastercode.salvago.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.mastercode.salvago.R;
import com.mastercode.salvago.Register;
import com.mastercode.salvago.tools.AppSecurity;
import com.mastercode.salvago.tools.MySession;
import com.mastercode.salvago.tools.Statictools;

public class Fg_Rregister_Step4 extends Fragment implements View.OnClickListener{

    TextView tvError;
    EditText tvCorreo, tvPsw, tvRepeat;
    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_register_step4, container,false);
        return init(v);
    }

    public View init(View v){

        tvError = v.findViewById(R.id.step_four_error);
        tvCorreo = v.findViewById(R.id.input_correo);
        tvRepeat = v.findViewById(R.id.input_repeat);
        tvPsw = v.findViewById(R.id.input_password);
        fab = v.findViewById(R.id.fabStep4);
        fab.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        isOk();
    }

    public void isOk(){

        if(!Statictools.isEmailValid(tvCorreo.getText().toString())){
            showError("El correo no es valido");
            return;
        }

        if(tvPsw.getText().toString().length() < 7){
            showError("La contraseña es muy corta");
            return;
        }

        if(!tvRepeat.getText().toString().equals(tvPsw.getText().toString())){
            showError("Las contraseñas no coinciden");
            return;
        }

        MySession.newcompany.correo = tvCorreo.getText().toString();
        MySession.newcompany.encryptpass = AppSecurity.Encrypt(tvPsw.getText().toString());
        Register.nextStep();
    }

    private void showError(String a){
        tvError.setVisibility(View.VISIBLE);
        tvError.setText(a);
    }
}
