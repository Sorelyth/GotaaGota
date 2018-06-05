package com.example.user.gotaagota.adaptadores;

/**
 * Created by User on 5/06/2018.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.gotaagota.R;
import com.example.user.gotaagota.objetos.cliente;

import java.util.ArrayList;


/**
 * Created by User on 5/06/2018.
 */

public class AdaptadorCliente extends RecyclerView.Adapter<AdaptadorCliente.clienteViewHolder> {
    private ArrayList<cliente> clientes;
    private OnclienteClickListener clickListener;

    public AdaptadorCliente(ArrayList<cliente> clientes, OnclienteClickListener clickListener){
        this.clientes = clientes;
        this.clickListener = clickListener;
    }

    @Override
    public clienteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_cliente,parent,false);
        return new clienteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(clienteViewHolder holder, int position) {
        final cliente p = clientes.get(position);
        holder.foto.setImageResource(R.drawable.user_person);
        holder.cedula.setText(p.getId());
        holder.nombre.setText(p.getNombre()+" "+p.getApellido());
        holder.barrio.setText(p.getBarrio());
        holder.numero_deuda.setText(p.getDeudas().size()+"");
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onclienteClick(p);
            }
        });
    }

    @Override
    public int getItemCount() {
        return clientes.size();
    }

    public static class clienteViewHolder extends RecyclerView.ViewHolder{
        private ImageView foto;
        private TextView cedula;
        private TextView nombre;
        private TextView barrio;
        private TextView numero_deuda;
        private View v;

        public clienteViewHolder(View itemView){
            super(itemView);
            v = itemView;
            foto = v.findViewById(R.id.imgFoto);
            cedula = v.findViewById(R.id.lblCedula);
            nombre = v.findViewById(R.id.lblNombre);
            barrio = v.findViewById(R.id.lblbarrio);
            numero_deuda = v.findViewById(R.id.lbldeudas);
        }

    }

    public interface OnclienteClickListener{
        void onclienteClick(cliente p);
    }
}
