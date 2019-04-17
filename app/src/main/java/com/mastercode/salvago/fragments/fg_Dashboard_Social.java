package com.mastercode.salvago.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mastercode.salvago.R;
import com.mastercode.salvago.database.Cloud;
import com.mastercode.salvago.tools.MySession;

public class fg_Dashboard_Social extends Fragment implements View.OnClickListener, ValueEventListener {

    DatabaseReference ref;
    EditText fb,tw,is;
    Button save;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_dashboard_social,container,false);
        return init(v);
    }

    private View init(View v){
        fb = v.findViewById(R.id.inputdash_facebook);
        tw = v.findViewById(R.id.inputdash_twitter);
        is = v.findViewById(R.id.inputdash_instagram);


        String companytype = MySession.dashcompany.companytype;
        String companyid = MySession.dashcompany.id;

        ref = new Cloud().getSocialNets(companytype,companyid);
        ref.addListenerForSingleValueEvent(this);

        save = v.findViewById(R.id.save);
        save.setOnClickListener(this);
        return v;
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        String txtFb = dataSnapshot.child("facebook").getValue().toString();
        String txtTw = dataSnapshot.child("twitter").getValue().toString();
        String txtIs = dataSnapshot.child("instagram").getValue().toString();

        fb.setText(txtFb);
        tw.setText(txtTw);
        is.setText(txtIs);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }

    @Override
    public void onClick(View v) {

        String txtFb = fb.getText().toString();
        String txtTw = tw.getText().toString();
        String txtIs = is.getText().toString();

        if(!txtFb.isEmpty()){
            ref.child("facebook").setValue(txtFb);
        }if(!txtIs.isEmpty()){
            ref.child("instagram").setValue(txtIs);
        }if(!txtTw.isEmpty()){
            ref.child("twitter").setValue(txtTw);
        }

        Snackbar.make(v,"Redes sociales actualizadas",Snackbar.LENGTH_LONG).show();
    }
}
