package com.mastercode.salvago.fragments;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.database.DatabaseReference;
import com.mastercode.salvago.R;
import com.mastercode.salvago.database.Cloud;
import com.mastercode.salvago.models.Chiptag;
import com.plumillonforge.android.chipview.Chip;
import com.plumillonforge.android.chipview.ChipView;
import com.plumillonforge.android.chipview.OnChipClickListener;
import java.util.ArrayList;
import java.util.List;

public class Fg_Dashboard_Tags extends Fragment implements View.OnClickListener, OnChipClickListener {

    ChipView chipsview;
    List<Chip> tags;
    EditText tagbox;
    Button add,save;
    DatabaseReference ref;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_dashboard_tags, container,false);
        return init(v);
    }

    View init(View v){
        tags = new ArrayList<>();
        ref = new Cloud().getTags("@testcompany1");

        chipsview = v.findViewById(R.id.chipview);
        add = v.findViewById(R.id.addbutton);
        save = v.findViewById(R.id.savebutton);
        tagbox = v.findViewById(R.id.tagbox);

        chipsview.setChipList(tags);
        chipsview.setOnChipClickListener(this);
        add.setOnClickListener(this);
        save.setOnClickListener(this);
        return v;
    }

    private void addNewTags(){

        String builder[] = tagbox.getText().toString().trim().split(",");
        for(String tag : builder){
            if(!chipsview.getAdapter().getChipList().contains(new Chiptag(tag.trim()))){
                chipsview.getAdapter().add(new Chiptag(tag));
            }
        }
        tagbox.setText("");
        //Esconder Teclado
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(tagbox.getWindowToken(), 0);
    }


    @Override
    public void onClick(View v) {
        switch (v.getTag().toString()){
            case "add":
                addNewTags();
                break;
            case "save":
                for (Chip tag : tags){
                    ref.push().setValue(tag.getText());
                }
                chipsview.setChipBackgroundColor(getResources().getColor(R.color.colorBueno));
                break;
        }
    }

    @Override
    public void onChipClick(final Chip chip) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle("Eliminar etiqueta?");
        dialog.setMessage(chip.getText());
        dialog.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                chipsview.getAdapter().remove(chip);
            }
        });
        dialog.setNegativeButton("Cancelar", null);
        dialog.create().show();
    }
}
