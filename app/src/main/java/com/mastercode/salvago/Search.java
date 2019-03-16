package com.mastercode.salvago;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class Search extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    EditText searchbox;
    SeekBar distancia;
    TextView textdistancia;
    RadioGroup promogroup;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Busqueda");
        init();
    }

    void init(){
       fab = findViewById(R.id.fab);
       fab.setOnClickListener(this);
       searchbox = findViewById(R.id.searchbox);
       distancia = findViewById(R.id.distancia);
       textdistancia = findViewById(R.id.distanciatext);
       distancia.setOnSeekBarChangeListener(this);
       promogroup = findViewById(R.id.group_promos);
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (progress == 0){
            textdistancia.setText("Rango de Distancia: Negocios mas cercanos");
        }else if(progress == 10){
            textdistancia.setText("Rango de Distancia: Negocios de todo el pais");
        }else{
            textdistancia.setText("Rango de Distancia: (" + progress * 10 + "km)");
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View v) {
        String search = searchbox.getText().toString();
        int kms = distancia.getProgress() * 10;
        int choose = promogroup.getCheckedRadioButtonId();
        boolean promo = (choose == R.id.promosi) ? true : false;

        if(!search.isEmpty()){
            Intent i = new Intent();
            i.putExtra("search", search);
            i.putExtra("kms", kms);
            i.putExtra("promo", promo);
            setResult(1, i);
            this.finish();
        }else{
            Snackbar.make(v, "Texto incompleto", Snackbar.LENGTH_LONG);
        }
    }


}
