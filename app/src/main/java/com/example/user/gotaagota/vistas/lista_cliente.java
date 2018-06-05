package com.example.user.gotaagota.vistas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.user.gotaagota.R;
import com.example.user.gotaagota.adaptadores.AdaptadorCliente;
import com.example.user.gotaagota.adaptadores.AdaptadorDeuda;
import com.example.user.gotaagota.objetos.BD;
import com.example.user.gotaagota.objetos.cliente;
import com.example.user.gotaagota.objetos.deuda;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class lista_cliente extends AppCompatActivity implements AdaptadorCliente.OnclienteClickListener{
    private RecyclerView rcv;
    private LinearLayoutManager llm;
    private ArrayList<cliente> clientes;
    private AdaptadorCliente adapter;
    private DatabaseReference databaseReference;
    private String bd = "gota";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cliente);
        rcv = findViewById(R.id.listacliente);
        clientes = new ArrayList<>();
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new AdaptadorCliente(clientes,this);
        rcv.setLayoutManager(llm);
        rcv.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(bd).child(BD.getId()).child("clientes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                clientes.clear();
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        cliente c = snapshot.getValue(cliente.class);
                        clientes.add(c);
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
    public void onclienteClick(cliente p) {

    }
}
