package com.example.user.gotaagota.vistas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.user.gotaagota.R;
import com.example.user.gotaagota.objetos.BD;
import com.example.user.gotaagota.objetos.cliente;
import com.example.user.gotaagota.objetos.deuda;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class registrar_deuda extends AppCompatActivity {
    private static String dbnombre = "gota";
    private static DatabaseReference db = FirebaseDatabase.getInstance().getReference();
    private EditText t1,t2,t3;
    private RadioButton r1,r2,r3,r4,r5,r6,r7,r8;
    private RadioGroup g1;
    private boolean v1=false,v2=false,v3=false,v4=false,v5=false,v6=false,v7=false,v8=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_deuda);
        t1 = findViewById(R.id.cedula);
        t2 = findViewById(R.id.v_prestamo);
        t3 = findViewById(R.id.cuotas);
        g1 = findViewById(R.id.grupop);
        r1 = findViewById(R.id.d1);
        r2 = findViewById(R.id.d2);
        r3 = findViewById(R.id.d3);
        r4 = findViewById(R.id.d4);
        r5 = findViewById(R.id.d5);
        r6 = findViewById(R.id.d6);
        r7 = findViewById(R.id.d7);
        r8 = findViewById(R.id.dd);
    }
    public void guardar(View v){
        int prestamo = Integer.parseInt(t2.getText().toString().trim());
        int cuota = Integer.parseInt(t3.getText().toString().trim());
        int num = new deuda().valor_interes(g1.getCheckedRadioButtonId());
        final deuda auxdeuda = new deuda(prestamo,num,cuota,lista_dias());
        final String ced = t1.getText().toString().trim();
        db.child(dbnombre).child(BD.getId()).child("clientes").addListenerForSingleValueEvent(new ValueEventListener() {
            int i=0;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()) {
                        cliente auxcliente = snapshot.getValue(cliente.class);
                        if (auxcliente.getId().equals(ced)){
                            auxcliente.getDeudas().add(auxdeuda);
                            db.child(dbnombre).child(BD.getId()).child("clientes").child(i+"").setValue(auxcliente);
                            Toast.makeText(getApplicationContext(),"Guardado",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        i++;
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"No hay clientes guardados",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void rbutom(View v){

        switch(v.getId()) {
            case R.id.d1:
                if(v1==false){
                    r1.setChecked(true);
                    v1=true;
                    v8=false;
                    r8.setChecked(false);
                }else{
                    r1.setChecked(false);
                    v1=false;
                }
                return;
            case R.id.d2:
                if(v2==false){
                    r2.setChecked(true);
                    v2=true;
                    v8=false;
                    r8.setChecked(false);
                }else{
                    r2.setChecked(false);
                    v2=false;
                }
                return;
            case R.id.d3:
                if(v3==false){
                    r3.setChecked(true);
                    v3=true;
                    v8=false;
                    r8.setChecked(false);
                }else{
                    r3.setChecked(false);
                    v3=false;
                }
                return;
            case R.id.d4:
                if(v4==false){
                    r4.setChecked(true);
                    v4=true;
                    v8=false;
                    r8.setChecked(false);
                }else{
                    r4.setChecked(false);
                    v4=false;
                }
                return;
            case R.id.d5:
                if(v5==false){
                    r5.setChecked(true);
                    v5=true;
                    v8=false;
                    r8.setChecked(false);
                }else{
                    r5.setChecked(false);
                    v5=false;
                }
                return;
            case R.id.d6:
                if(v6==false){
                    r6.setChecked(true);
                    v6=true;
                    v8=false;
                    r8.setChecked(false);
                }else{
                    r6.setChecked(false);
                    v6=false;
                }
                return;
            case R.id.d7:
                if(v7==false){
                    r7.setChecked(true);
                    v7=true;
                    v8=false;
                    r8.setChecked(false);
                }else{
                    r7.setChecked(false);
                    v7=false;
                }
                return;
            case R.id.dd:
                if(v8==false){
                    r8.setChecked(true);
                    v8=true;
                    v1=false;
                    r1.setChecked(false);
                    v2=false;
                    r2.setChecked(false);
                    v3=false;
                    r3.setChecked(false);
                    v4=false;
                    r4.setChecked(false);
                    v5=false;
                    r5.setChecked(false);
                    v6=false;
                    r6.setChecked(false);
                    v7=false;
                    r7.setChecked(false);
                }else{
                    r8.setChecked(false);
                    v8=false;
                }
                return;
        }

    }
    public ArrayList<String> lista_dias(){
        ArrayList<String> auxdias = new ArrayList<>();
        if(r8.isChecked()==true){
            auxdias.add("todos");
            return auxdias;
        }

        if(r1.isChecked()==true){
            auxdias.add("lunes");
        }

        if(r2.isChecked()==true){
            auxdias.add("martes");
        }

        if(r3.isChecked()==true){
            auxdias.add("miercoles");
        }

        if(r4.isChecked()==true){
            auxdias.add("jueves");
        }

        if(r5.isChecked()==true){
            auxdias.add("viernes");
        }

        if(r6.isChecked()==true){
            auxdias.add("sabado");
        }

        if(r7.isChecked()==true){
            auxdias.add("domingo");
        }
        return auxdias;
    }
}
