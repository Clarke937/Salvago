package com.mastercode.salvago.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mastercode.salvago.R;
import com.mastercode.salvago.database.Cloud;
import com.mastercode.salvago.models.Opinion;
import com.mastercode.salvago.tools.MySession;

import java.util.List;

public class Fg_Dashboard_Rating extends Fragment implements View.OnClickListener, ValueEventListener {

    LinearLayout bt1,bt2,bt3;
    TextView tvGood,tvBad,tvNormal, tvValue, tvText;
    DatabaseReference ref;
    List<Opinion> opinions;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_dashboard_rating,container,false );
        getActivity().setTitle("Evaluaciones");
        return init(v);
    }

    public View init(View v){

        bt1 = v.findViewById(R.id.btnRating1);
        bt2 = v.findViewById(R.id.btnRating2);
        bt3 = v.findViewById(R.id.btnRating3);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);

        tvBad = v.findViewById(R.id.badCounter);
        tvText = v.findViewById(R.id.rating_text);
        tvGood = v.findViewById(R.id.goodCounter);
        tvValue = v.findViewById(R.id.rating_value);
        tvNormal = v.findViewById(R.id.normalCounter);


        String companytype = MySession.dashcompany.companytype;
        String companyid = MySession.dashcompany.id;
        ref = new Cloud().getRatingOfCompany(companytype,companyid);
        ref.addListenerForSingleValueEvent(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getTag().toString()){
            case "0":
                break;
            case "1":
                break;
            case "2":
                break;
        }
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        int goods = 0, bads = 0, normal = 0, suma =0;

        for(DataSnapshot d : dataSnapshot.getChildren()){
            int points = Integer.parseInt(d.child("points").getValue().toString());
            suma += points;
            switch (points){
                case 5: case 4:
                    goods += 1;
                    break;
                case 3:
                    normal += 1;
                    break;
                case 2: case 1:
                    bads += 1;
                    break;
            }
        }

        tvBad.setText(bads + "");
        tvGood.setText(goods + "");
        tvNormal.setText(normal + "");

        double rate = Math.floor(suma/dataSnapshot.getChildrenCount());
        tvValue.setText(Math.round(rate) + "/5");

        switch ((int)Math.round(rate)){
            case 1:
                tvText.setText("Muy Malo");
                break;
            case 2:
                tvText.setText("Malo");
                break;
            case 3:
                tvText.setText("Regular");
                break;
            case 4:
                tvText.setText("Bueno");
                break;
            case 5:
                tvText.setText("Excelente!");
                break;
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
