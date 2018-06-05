package com.example.user.gotaagota;

import android.app.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.os.VibrationEffect;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.example.user.gotaagota.adaptadores.AdaptadorDeuda;
import com.example.user.gotaagota.objetos.BD;
import com.example.user.gotaagota.objetos.cliente;
import com.example.user.gotaagota.objetos.deuda;
import com.example.user.gotaagota.objetos.prestador;
import com.example.user.gotaagota.vistas.lista_cliente;
import com.example.user.gotaagota.vistas.registrar_clientes;
import com.example.user.gotaagota.vistas.registrar_deuda;
import com.example.user.gotaagota.vistas.reporte;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Principal extends AppCompatActivity implements AdaptadorDeuda.OndeudaClickListener {
    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private FrameLayout fm;
    private RecyclerView rcv;
    private LinearLayoutManager llm;
    private ArrayList<deuda> deudas;
    private AdaptadorDeuda adapter;
    private DatabaseReference databaseReference;
    private String bd = "gota";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        mPlanetTitles = getResources().getStringArray(R.array.lista_menu_lateral);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerList = findViewById(R.id.left_drawer);
        fm = findViewById(R.id.content_frame);
        rcv = findViewById(R.id.listadeuda);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapter, View view, int position, long arg) {
                switch (position){
                    case 0:
                        startActivity(new Intent(Principal.this,registrar_clientes.class));
                        break;
                    case 1:
                        startActivity(new Intent(Principal.this,registrar_deuda.class));
                        break;
                    case 2:
                        startActivity(new Intent(Principal.this,lista_cliente.class));
                        break;
                    case 3:
                        startActivity(new Intent(Principal.this,reporte.class));
                        break;
                }
            }
        });

        deudas = new ArrayList<>();
        llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new AdaptadorDeuda(deudas,this);
        rcv.setLayoutManager(llm);
        rcv.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(bd).child(BD.getId()).child("clientes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                deudas.clear();
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        cliente c = snapshot.getValue(cliente.class);
                        for (deuda d:c.getDeudas()) {
                            if(d.getDias_cobro().contains("lunes") || d.getDias_cobro().contains("todos")){
                                adapter.getNom_ape().add(c.getNombre()+" "+c.getApellido());
                                adapter.getBarrio().add(c.getBarrio());
                                deudas.add(d);
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
    public void ondeudaClick(deuda p) {

    }
}
