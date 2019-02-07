package com.mastercode.salvago.tools;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class Popups {

    private Context ctx;

    public Popups(Context c){
        this.ctx = c;
    }

    public AlertDialog CloseApp(DialogInterface.OnClickListener listener){
        AlertDialog.Builder dialog = new AlertDialog.Builder(ctx);
        dialog.setTitle("Cerrar aplicacion");
        dialog.setMessage("Desea salir de la aplicacion SalvaGo?");
        dialog.setPositiveButton("Si",listener);
        dialog.setNegativeButton("Cancelar",null);
        return dialog.create();
    }


}
