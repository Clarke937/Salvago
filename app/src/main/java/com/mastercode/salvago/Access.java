package com.mastercode.salvago;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mastercode.salvago.tools.AppNavigation;
import com.mastercode.salvago.tools.MySession;

public class Access extends AppCompatActivity implements View.OnClickListener{

    FloatingActionButton fab;
    FirebaseAuth auth;
    FirebaseUser fuser;
    EditText etCompany,etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
    }

    public void init(){
        fab = findViewById(R.id.fab);
        etCompany = findViewById(R.id.input_company);
        etPass = findViewById(R.id.input_password);

        FirebaseApp app = FirebaseApp.getInstance("secondary");
        auth = FirebaseAuth.getInstance(app);
        MySession.fbuser = auth.getCurrentUser();
        fab.setOnClickListener(this);
        goDashboard();
    }

    /*defaultpass*/
    @Override
    public void onClick(View v) {

        /*String mail = "correo" + etCompany.getText().toString() + ".com";
        String pass = etPass.getText().toString();
        Log.e("AUTHERROR",pass);*/

        /*auth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful() && task.getResult() != null){
                    AuthResult result = task.getResult();
                    MySession.fbuser = result.getUser();
                    goDashboard();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("AUTHERROR",e.getMessage());
                Log.e("AUTHERROR",e.toString());
            }
        });*/
    }


    public void goDashboard(){
        AppNavigation.goDashboard(this);
        /*if(MySession.fbuser != null){

        }*/
    }


}
