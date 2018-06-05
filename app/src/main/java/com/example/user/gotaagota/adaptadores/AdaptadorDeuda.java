package com.example.user.gotaagota.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.gotaagota.R;
import com.example.user.gotaagota.objetos.deuda;

import java.util.ArrayList;

/**
 * Created by User on 5/06/2018.
 */

public class AdaptadorDeuda extends RecyclerView.Adapter<AdaptadorDeuda.deudaViewHolder> {
    private ArrayList<deuda> deudas;
    private OndeudaClickListener clickListener;

    public ArrayList<String> getNom_ape() {
        return nom_ape;
    }

    public void setNom_ape(ArrayList<String> nom_ape) {
        this.nom_ape = nom_ape;
    }

    public ArrayList<String> getBarrio() {
        return barrio;
    }

    public void setBarrio(ArrayList<String> barrio) {
        this.barrio = barrio;
    }

    private ArrayList<String> nom_ape,barrio;



    public AdaptadorDeuda(ArrayList<deuda> deudas, OndeudaClickListener clickListener){
        this.deudas = deudas;
        this.nom_ape = new ArrayList<>();
        this.barrio = new ArrayList<>();
        this.clickListener = clickListener;
    }

    @Override
    public deudaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_deuda,parent,false);
        return new deudaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(deudaViewHolder holder, int position) {
        final deuda p = deudas.get(position);
        final String na = nom_ape.get(position);
        final String ba= barrio.get(position);
        holder.foto.setImageResource(R.drawable.money);
        holder.nombre_apellido.setText(na);
        holder.barrio.setText(ba);
        holder.cuota.setText(p.ValorCuota()+"");
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.ondeudaClick(p);
            }
        });
    }

    @Override
    public int getItemCount() {
        return deudas.size();
    }

    public static class deudaViewHolder extends RecyclerView.ViewHolder{
        private ImageView foto;
        private TextView nombre_apellido;
        private TextView barrio;
        private TextView cuota;
        private View v;

        public deudaViewHolder(View itemView){
            super(itemView);
            v = itemView;
            foto = v.findViewById(R.id.imgFoto);
            nombre_apellido = v.findViewById(R.id.lblnombre);
            barrio = v.findViewById(R.id.lblbarrio);
            cuota = v.findViewById(R.id.lblcuota);
        }

    }

    public interface OndeudaClickListener{
        void ondeudaClick(deuda p);
    }
}
