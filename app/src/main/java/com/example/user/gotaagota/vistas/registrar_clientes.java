package com.example.user.gotaagota.vistas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.gotaagota.R;
import com.example.user.gotaagota.objetos.BD;
import com.example.user.gotaagota.objetos.cliente;
import com.example.user.gotaagota.objetos.prestador;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class registrar_clientes extends AppCompatActivity {
    private Button boton;
    private EditText t1,t2,t3,t4,t5;
    private static String dbnombre = "gota";
    private static DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_clientes);
        boton = findViewById(R.id.boton);
        t1 = findViewById(R.id.cedula);
        t2 = findViewById(R.id.nombre);
        t3 = findViewById(R.id.apellido);
        t4 = findViewById(R.id.telefono);
        t5 = findViewById(R.id.barrio);
    }
    public void guardar(View v){
        db.child(dbnombre).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cliente auxcliente= new cliente(t1.getText().toString(),t2.getText().toString(),
                        t3.getText().toString(),t4.getText().toString(),t5.getText().toString());
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        prestador p = snapshot.getValue(prestador.class);
                        if(p.getId().equals(BD.getId())){
                            if(p.getclientes().size()==0){
                                p.getclientes().add(auxcliente);
                                db.child(dbnombre).child(p.getId()).setValue(p);
                                Toast.makeText(getApplicationContext(),"Guardado",Toast.LENGTH_SHORT).show();
                                limpiar();
                            }else{
                                if(p.getclientes().contains(auxcliente)){
                                    Toast.makeText(getApplicationContext(),"!Ya se encontraba Registrado¡",Toast.LENGTH_SHORT).show();
                                }else{
                                    p.getclientes().add(auxcliente);
                                    db.child(dbnombre).child(p.getId()).setValue(p);
                                    Toast.makeText(getApplicationContext(),"Guardado",Toast.LENGTH_SHORT).show();
                                    limpiar();
                                }
                            }
                           return;
                        }
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void limpiar(){
        t1.setText("");
        t2.setText("");
        t3.setText("");
        t4.setText("");
        t5.setText("");
    }
}
