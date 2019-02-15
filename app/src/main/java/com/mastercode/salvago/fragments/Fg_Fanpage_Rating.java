package com.mastercode.salvago.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mastercode.salvago.Fanpage;
import com.mastercode.salvago.R;
import com.mastercode.salvago.adapters.Fanpage_Comments;
import com.mastercode.salvago.database.Cloud;
import com.mastercode.salvago.models.Opinion;
import com.mastercode.salvago.tools.Statictools;
import com.willy.ratingbar.ScaleRatingBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Fg_Fanpage_Rating extends Fragment implements ValueEventListener, View.OnClickListener {

    DatabaseReference ref;
    List<Opinion> opiniones;
    TextView tvValue, tvAttribute;
    ImageView star;
    Button btnOpinion;
    Fanpage_Comments adapter;
    ListView list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_fanpage_rating,container,false);
        return init(v);
    }

    public View init(View v){
        opiniones = new ArrayList<>();
        list = v.findViewById(R.id.list);
        star = v.findViewById(R.id.wave_star);
        tvValue = v.findViewById(R.id.rating_value);
        btnOpinion = v.findViewById(R.id.btn_opinion);
        tvAttribute = v.findViewById(R.id.rating_text);
        adapter = new Fanpage_Comments(getContext(), opiniones);
        list.setAdapter(adapter);


        btnOpinion.setOnClickListener(this);

        ref = new Cloud().getRatingOfCompany(Fanpage.companytype, Fanpage.companyid);
        load();
        return v;
    }

    public void load(){
        ref.addListenerForSingleValueEvent(this);
    }


    @Override
    public void onClick(View v) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_opinion, null, false);
        final EditText etOp = view.findViewById(R.id.dialog_opinion);
        final ScaleRatingBar stars = view.findViewById(R.id.starbar);

        dialog.setView(view);
        dialog.setPositiveButton("Publicar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = etOp.getText().toString();
                int note = (int) stars.getRating();
                postOpinion(text, note);
            }
        }).setNegativeButton("Cancelar", null);
        dialog.create().show();
    }

    private void UpdateRatingValue(){
        double value = 0;
        for(Opinion o : opiniones){
            value += o.points;
        }
        value /= opiniones.size();
        value = Math.floor(value);

        String textAtt = "Excelente!";

        switch ((int)Math.round(value)){
            case 1:
                textAtt = "Muy Malo";
                break;
            case 2:
                textAtt = "Malo";
                break;
            case 3:
                textAtt = "Regular";
                break;
            case 4:
                textAtt = "Bueno";
                break;
            case 5:
                textAtt = "Excelente!";
                break;
        }

        tvAttribute.setText(textAtt);
        tvValue.setText(Math.round(value) + "/5");
    }


    public void postOpinion(String text, int val){
        Map<String,Object> data = new ArrayMap<>();
        data.put("text", text);
        data.put("points", val);
        data.put("date", Statictools.getSimpleDate());
        ref.push().setValue(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                load();
            }
        });
    }


    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        opiniones.clear();
        for(DataSnapshot d : dataSnapshot.getChildren()){
            Opinion op = new Opinion();
            op.points = Integer.parseInt(d.child("points").getValue().toString());
            op.date = d.child("date").getValue().toString();
            op.comment = d.child("text").getValue().toString();
            opiniones.add(op);
            if(opiniones.size() >= 50) break;
        }
        adapter.notifyDataSetChanged();
        UpdateRatingValue();
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
