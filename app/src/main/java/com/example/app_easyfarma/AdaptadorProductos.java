package com.example.app_easyfarma;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.UsuarioViewHolder> {

    List<Producto> listaProductos;
    Context context;
    String url="https://giancaproject1.000webhostapp.com/imagenesf/";

    public AdaptadorProductos(Context context, List<Producto> listaProductos) {
        this.context = context;
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rv_producto, viewGroup, false);
        return new UsuarioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder usuarioViewHolder, final int i) {

        Producto p=listaProductos.get(i);
        usuarioViewHolder.tvId.setText(listaProductos.get(i).getId());
        usuarioViewHolder.tvProducto.setText(listaProductos.get(i).getProducto());
        usuarioViewHolder.tvPrecio.setText(listaProductos.get(i).getPrecio());
        usuarioViewHolder.tvUbicacion.setText(listaProductos.get(i).getUbicacion());
        Glide.with(context)
                .load(url+p.getProducto()+".jpg")
                .asBitmap()
                .error(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(usuarioViewHolder.image1);


        usuarioViewHolder.cvTarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetallesProducto.class);
                intent.putExtra("producto", listaProductos.get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder {

        ImageView image1;
        CardView cvTarjeta;
        TextView tvId, tvProducto, tvPrecio, tvUbicacion;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);

            cvTarjeta = itemView.findViewById(R.id.cvTarjeta);
            tvId = itemView.findViewById(R.id.tvId);
            tvProducto = itemView.findViewById(R.id.tvProducto);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvUbicacion = itemView.findViewById(R.id.tvUbicacion);
            image1=itemView.findViewById(R.id.image1);
        }
    }

    public void filtrar(ArrayList<Producto> filtroUsuarios) {
        this.listaProductos = filtroUsuarios;
        notifyDataSetChanged();
    }
}
