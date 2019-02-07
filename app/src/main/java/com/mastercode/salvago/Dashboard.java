package com.mastercode.salvago;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {

    FloatingActionButton fab;
    LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        init();
    }

    public void init(){

        chart = findViewById(R.id.viewchart);

        List<Entry> entries = new ArrayList<>();

        entries.add(new Entry(0,100));
        entries.add(new Entry(1,200));
        entries.add(new Entry(2,300));

        LineDataSet set = new LineDataSet(entries,"Label");
        set.setColors(R.color.colorAccent);
        set.setValueTextColor(R.color.colorText);
        set.setDrawFilled(true);
        set.setCircleRadius(5);


        LineData data = new LineData(set);
        chart.setData(data);
        chart.invalidate();
    }


}
