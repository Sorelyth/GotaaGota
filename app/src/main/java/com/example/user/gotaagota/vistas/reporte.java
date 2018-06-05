package com.example.user.gotaagota.vistas;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.user.gotaagota.R;
import com.example.user.gotaagota.objetos.BD;
import com.example.user.gotaagota.objetos.cliente;
import com.example.user.gotaagota.objetos.deuda;
import com.example.user.gotaagota.objetos.prestador;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class reporte extends AppCompatActivity {
    PieChart piechart;
    private DatabaseReference databaseReference;
    private String bd = "gota";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);
        piechart = findViewById(R.id.grafica);
        piechart.setUsePercentValues(true);
        piechart.getDescription().setEnabled(false);
        piechart.setExtraOffsets(5,10,5,5);
        piechart.setDragDecelerationFrictionCoef(0.95f);
        piechart.setDrawHoleEnabled(true);
        piechart.setHoleColor(Color.BLACK);
        piechart.setTransparentCircleRadius(61f);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        piechart.animateY(2000, Easing.EasingOption.EaseInCirc);

        databaseReference.child(bd).addListenerForSingleValueEvent(new ValueEventListener() {
            ArrayList<PieEntry> valores = new ArrayList<>();
            int deben=0;
            int intereses=0;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data:dataSnapshot.getChildren()) {
                    prestador p = data.getValue(prestador.class);
                    if (p.getId().equals(BD.getId())) {
                        valores.add(new PieEntry(p.getCapital(), "capital"));
                        for (cliente c : p.getclientes()) {
                            for (deuda d : c.getDeudas()) {
                                deben += d.debecapital();
                                intereses += d.debeintereses();
                            }
                        }
                        valores.add(new PieEntry(deben, "prestado"));
                        valores.add(new PieEntry(intereses, "intereses"));
                        PieDataSet dataSet = new PieDataSet(valores, "REPORTE");
                        dataSet.setSliceSpace(3f);
                        dataSet.setSelectionShift(5f);
                        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                        PieData data1 = new PieData(dataSet);
                        data1.setValueTextSize(10f);
                        data1.setValueTextColor(Color.YELLOW);
                        piechart.setData(data1);
                        return;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
