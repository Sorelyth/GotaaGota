package com.example.user.gotaagota.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.gotaagota.R;
import com.example.user.gotaagota.objetos.cliente;
import com.example.user.gotaagota.objetos.deuda;

import java.util.ArrayList;

/**
 * Created by User on 5/06/2018.
 */


public class AdaptadorConsulta  extends RecyclerView.Adapter<AdaptadorConsulta .consultaViewHolder> {
    private ArrayList<deuda> deudas;
    private OnconsultaClickListener clickListener;
    private ArrayList<String> nombres = new ArrayList<>();

    public ArrayList<String> getNombres() {
        return nombres;
    }

    public void setNombres(ArrayList<String> nombres) {
        this.nombres = nombres;
    }

    public AdaptadorConsulta(ArrayList<deuda> deudas, OnconsultaClickListener clickListener){
        this.deudas = deudas;
        this.clickListener = clickListener;
    }

    @Override
    public consultaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_consulta,parent,false);
        return new consultaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(consultaViewHolder holder, int position) {
        final deuda p = deudas.get(position);
        holder.foto.setImageResource(R.drawable.user_person);
        holder.nombre.setText(nombres.get(position));
        holder.prestado.setText(p.getValor_prestado()+"");
        holder.tasa.setText(p.intereses()+"");
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onconsultaClick(p);
            }
        });
    }

    @Override
    public int getItemCount() {
        return deudas.size();
    }

    public static class consultaViewHolder extends RecyclerView.ViewHolder{
        private ImageView foto;
        private TextView tasa;
        private TextView nombre;
        private TextView prestado;
        private View v;

        public consultaViewHolder(View itemView){
            super(itemView);
            v = itemView;
            foto = v.findViewById(R.id.imgFoto);
            nombre = v.findViewById(R.id.lblnombre);
            prestado = v.findViewById(R.id.lblvaloreprestamo);
            tasa = v.findViewById(R.id.lblintereses);
        }

    }

    public interface OnconsultaClickListener{
        void onconsultaClick(deuda p);
    }
}
