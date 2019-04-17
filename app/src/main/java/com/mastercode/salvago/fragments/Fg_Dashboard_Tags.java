package com.mastercode.salvago.fragments;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mastercode.salvago.R;
import com.mastercode.salvago.database.Cloud;
import com.mastercode.salvago.models.Chiptag;
import com.mastercode.salvago.tools.MySession;
import com.plumillonforge.android.chipview.Chip;
import com.plumillonforge.android.chipview.ChipView;
import com.plumillonforge.android.chipview.OnChipClickListener;
import java.util.ArrayList;
import java.util.List;

public class Fg_Dashboard_Tags extends Fragment implements View.OnClickListener, OnChipClickListener, ValueEventListener {

    Button add;
    //Button borrar;
    EditText tagbox;
    List<Chip> tags;
    Chip trash = null;
    ChipView chipsview;
    DatabaseReference ref;
    boolean deleting = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fg_dashboard_tags, container,false);
        getActivity().setTitle("Etiquetas");
        return init(v);
    }

    private View init(View v){
        tags = new ArrayList<>();


        String companyid = MySession.dashcompany.id;
        ref = new Cloud().getTags(companyid);

        chipsview = v.findViewById(R.id.chipview);
        add = v.findViewById(R.id.addbutton);
        //borrar = v.findViewById(R.id.trash);
        tagbox = v.findViewById(R.id.tagbox);

        chipsview.setChipList(tags);
        chipsview.setOnChipClickListener(this);
        ref.addListenerForSingleValueEvent(this);
        add.setOnClickListener(this);
        //borrar.setOnClickListener(this);
        return v;
    }

    private void addNewTags(View v){
        String builder[] = tagbox.getText().toString().trim().split(",");
        for(String tag : builder){
            if(!chipsview.getAdapter().getChipList().contains(new Chiptag(tag.trim()))){
                chipsview.getAdapter().add(new Chiptag(tag.trim()));
                ref.push().setValue(tag.trim());
            }
        }
        tagbox.setText("");

        //Esconder Teclado
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(tagbox.getWindowToken(), 0);

        Snackbar.make(v,"Etiquetas agregadas", Snackbar.LENGTH_SHORT).show();
    }

    private void borrarEtiquetas(View v){
        Snackbar.make(v,"Etiquetas borradas", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getTag().toString()){
            case "add":
                addNewTags(v);
                break;
            case "del":
                //borrarEtiquetas(v);
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
                DeleteTag(chip);
            }
        });
        dialog.setNegativeButton("Cancelar", null);
        dialog.create().show();
    }


    public void DeleteTag(Chip c){
        deleting = true;
        trash = c;
        chipsview.getAdapter().remove(c);
        ref.addListenerForSingleValueEvent(this);
    }

    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        for(DataSnapshot d : dataSnapshot.getChildren()){
            String tag = d.getValue().toString();
            if(deleting){
                if(tag.equals(trash.getText())) ref.child(d.getKey()).setValue(null);
            }else{
                chipsview.getAdapter().add(new Chiptag(tag));
            }
        }

        deleting = false;
        trash = null;
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
