package com.example.app_easyfarma;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.CarritoAdapterViewHolder> {

    private ArrayList<Producto> productos;
    private OnItemClick listener;

    public CarritoAdapter(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    @NonNull
    @Override
    public CarritoAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carrito,parent,false);
        return new CarritoAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarritoAdapterViewHolder holder, int position) {

        Producto producto = productos.get(position);
        holder.bind(producto);
    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    class CarritoAdapterViewHolder extends RecyclerView.ViewHolder{

        TextView tv_cantidad_confirmacion,tv_nombre_producto_confirmacion,tv_precio_confirmacion;
        ImageView imgEliminar;

        public CarritoAdapterViewHolder(@NonNull final View itemView) {
            super(itemView);

            tv_cantidad_confirmacion = itemView.findViewById(R.id.tv_cantidad_confirmacion);
            tv_nombre_producto_confirmacion = itemView.findViewById(R.id.tv_nombre_producto_confirmacion);
            tv_precio_confirmacion = itemView.findViewById(R.id.tv_precio_confirmacion);
            imgEliminar = itemView.findViewById(R.id.imgEliminar);

            imgEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION)
                        listener.itemClick(productos.get(position));

                    productos.remove(position);
                    notifyItemRemoved(position);


                }
            });
        }

        void bind(Producto producto){

            tv_cantidad_confirmacion.setText(String.valueOf(producto.cantidad));
            tv_nombre_producto_confirmacion.setText(producto.producto);
            tv_precio_confirmacion.setText(producto.precio);

        }
    }

    interface OnItemClick{
        void itemClick(Producto producto);
    }

    public void onItemClickListener(OnItemClick listener){
        this.listener = listener;
    }
}
