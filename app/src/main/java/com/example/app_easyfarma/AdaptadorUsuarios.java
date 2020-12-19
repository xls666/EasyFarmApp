package com.example.app_easyfarma;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorUsuarios extends RecyclerView.Adapter<AdaptadorUsuarios.UsuarioViewHolder> {

    Context context;
    List<Usuario> listaUsuarios;

    public AdaptadorUsuarios(Context context, List<Usuario> listaUsuarios) {
        this.context = context;
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_usuario, viewGroup, false);
        return new UsuarioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder usuarioViewHolder, final int i) {
        usuarioViewHolder.tvId.setText(listaUsuarios.get(i).getId());
        usuarioViewHolder.tvProducto.setText(listaUsuarios.get(i).getProducto());
        usuarioViewHolder.tvPrecio.setText(listaUsuarios.get(i).getPrecio());
        usuarioViewHolder.tvUbicacion.setText(listaUsuarios.get(i).getUbicacion());

        usuarioViewHolder.cvTarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetallesUsuario.class);
                intent.putExtra("usuario", listaUsuarios.get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {

        CardView cvTarjeta;
        TextView tvId, tvProducto, tvPrecio, tvUbicacion;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);

            cvTarjeta = itemView.findViewById(R.id.cvTarjeta);
            tvId = itemView.findViewById(R.id.tvId);
            tvProducto = itemView.findViewById(R.id.tvProducto);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvUbicacion = itemView.findViewById(R.id.tvUbicacion);
        }
    }

    public void filtrar(ArrayList<Usuario> filtroUsuarios) {
        this.listaUsuarios = filtroUsuarios;
        notifyDataSetChanged();
    }
}
