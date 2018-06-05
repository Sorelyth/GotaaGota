package com.example.user.gotaagota;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.gotaagota.objetos.BD;
import com.example.user.gotaagota.objetos.prestador;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class login extends AppCompatActivity {
    private String dbnombre = "gota";
    private DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    private EditText texto1,texto2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        texto1 = findViewById(R.id.user);
        texto2 = findViewById(R.id.password);
    }
    public void login(View view){

        db.child(dbnombre).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String n,n2;
                n=texto1.getText().toString();
                n2= texto2.getText().toString();
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        prestador p = snapshot.getValue(prestador.class);
                        if(p.getUsuario().equals(n) && p.getContraseña().equals(n2)){
                            BD.setId(p.getId());
                            Intent i = new Intent(login.this,Principal.class);
                            startActivity(i);
                            finish();
                            return;
                        }
                    }
                }
                Toast.makeText(getApplicationContext(),R.string.erro1,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void g_u(View v){
       startActivity(new Intent(login.this,consulta_cliente.class));
    }
}
