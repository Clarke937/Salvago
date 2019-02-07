package com.mastercode.salvago;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.mastercode.salvago.adapters.Adapter_Gifts;
import com.mastercode.salvago.models.Giftcard;
import com.mastercode.salvago.tools.AppNavigation;

import java.util.ArrayList;
import java.util.List;

public class Giftcards extends AppCompatActivity {

    private List<Giftcard> cards;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giftcards);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
    }

    public void init(){

        cards = new ArrayList<>();
        cards.add(new Giftcard("Promo 2x1","Esta es una increible promo del 2x1"));
        cards.add(new Giftcard("Promo X-Mas","Esta es una increible promo del 2x1"));
        cards.add(new Giftcard("Promo 10% Off","Esta es una increible promo del 2x1"));

        list = findViewById(R.id.listview);
        list.setAdapter(new Adapter_Gifts(cards,this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_gifts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_moregifts) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
