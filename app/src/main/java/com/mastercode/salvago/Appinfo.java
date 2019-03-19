package com.mastercode.salvago;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import babushkatext.BabushkaText;

public class Appinfo extends AppCompatActivity {

    BabushkaText bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appinfo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
    }

    public void init(){

        bt = findViewById(R.id.textstyle);

        bt.addPiece(new BabushkaText.Piece
                .Builder("Programadores:\n")
                .textColor(Color.parseColor("#000000"))
                .style(Typeface.BOLD)
                .build());

        bt.addPiece(new BabushkaText.Piece
                .Builder("Edgar Vladimir Retana Carranza\nRaisa Fabiola Ramírez Reyes")
                .textColor(Color.parseColor("#757575"))
                .build());

        bt.display();



    }

}
