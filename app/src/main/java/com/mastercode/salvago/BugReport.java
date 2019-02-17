package com.mastercode.salvago;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.mastercode.salvago.database.Cloud;

public class BugReport extends AppCompatActivity implements View.OnClickListener{

    FloatingActionButton fab;
    EditText etreport;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug_report);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Reporte de errores");

        etreport = findViewById(R.id.bugbox);
        fab = findViewById(R.id.fabug);
        fab.setOnClickListener(this);

        ref = new Cloud().getBugs();
    }

    @Override
    public void onClick(View v) {
        ref.push().setValue(etreport.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Thanks();
            }
        });
    }

    public void Thanks(){
        MaterialStyledDialog.Builder builder = new MaterialStyledDialog.Builder(this);
        builder.setTitle("Muchas Gracias!");
        builder.setDescription("Tu reporte ya fue enviado para ser analizado, resolveremos tu problema tan pronto como nos sea posible");
        builder.setStyle(Style.HEADER_WITH_ICON);
        builder.setIcon(R.drawable.ic_rating_white);
        builder.setHeaderColor(R.color.colorBueno);
        builder.setPositiveText("Entendido!");
        builder.show();
    }

}
