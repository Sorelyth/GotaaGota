package com.example.user.gotaagota;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.gotaagota.adaptadores.AdaptadorConsulta;
import com.example.user.gotaagota.adaptadores.AdaptadorDeuda;
import com.example.user.gotaagota.objetos.BD;
import com.example.user.gotaagota.objetos.cliente;
import com.example.user.gotaagota.objetos.deuda;
import com.example.user.gotaagota.objetos.prestador;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class consulta_cliente extends AppCompatActivity implements AdaptadorConsulta.OnconsultaClickListener {
    private EditText text;
    private RecyclerView recyclerView;
    private String dbnombre = "gota";
    private DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    private LinearLayoutManager llm;
    private ArrayList<deuda> deudas;
    private AdaptadorConsulta adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_cliente);
        text = findViewById(R.id.cedula);
        recyclerView = findViewById(R.id.listadeuda);

        deudas = new ArrayList<>();
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new AdaptadorConsulta(deudas,this);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);


    }
    public void ejecutar(View v){

        db.child(dbnombre).addListenerForSingleValueEvent(new ValueEventListener() {
            String cedu= text.getText().toString().trim();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                deudas.clear();
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        prestador p = snapshot.getValue(prestador.class);
                        for (cliente c:p.getclientes()) {
                            if(c.getId().equals(cedu)){
                                for (deuda d:c.getDeudas() ) {
                                    deudas.add(d);
                                    adapter.getNombres().add(p.getNombre()+" "+p.getApellido());
                                }
                            }
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onconsultaClick(deuda p) {

    }
}
