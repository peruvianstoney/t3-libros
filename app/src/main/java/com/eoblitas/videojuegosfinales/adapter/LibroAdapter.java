package com.eoblitas.videojuegosfinales.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eoblitas.videojuegosfinales.DetalleActivity;
import com.eoblitas.videojuegosfinales.R;
import com.eoblitas.videojuegosfinales.entities.Libro;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LibroAdapter extends RecyclerView.Adapter<LibroAdapter.ViewHolder> {

    private List<Libro> data;

    public LibroAdapter(List<Libro> data){
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_libro,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibroAdapter.ViewHolder holder, int position) {
        ImageView imgLibro = holder.itemView.findViewById(R.id.imgLibro);
        TextView tvNombreLibro = holder.itemView.findViewById(R.id.tvNombreLibro);
        Button btnDetalle = holder.itemView.findViewById(R.id.btnDetalle);


        tvNombreLibro.setText(data.get(position).getTitulo());
        String ruta = "https://6298a07ef2decf5bb7477e26.mockapi.io/"+data.get(position).getUrl_imagen();
        Picasso.get().load(ruta).resize (250, 250).into(imgLibro);
        btnDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detalle = new Intent(view.getContext(), DetalleActivity.class);
                detalle.putExtra("ID",data.get(holder.getAdapterPosition()).getId());
                detalle.putExtra("TITULO",data.get(holder.getAdapterPosition()).getTitulo());
                detalle.putExtra("RESUMEN",data.get(holder.getAdapterPosition()).getResumen());
                detalle.putExtra("AUTOR",data.get(holder.getAdapterPosition()).getAutor());
                detalle.putExtra("FECHA_PUBLICACION",data.get(holder.getAdapterPosition()).getFecha_publicacion());
                detalle.putExtra("TIENDA_UNO",data.get(holder.getAdapterPosition()).getTienda_1());
                detalle.putExtra("TIENDA_DOS",data.get(holder.getAdapterPosition()).getTienda_2());
                detalle.putExtra("TIENDA_TRES",data.get(holder.getAdapterPosition()).getTienda_3());
                detalle.putExtra("IMAGEN",data.get(holder.getAdapterPosition()).getUrl_imagen());
                view.getContext().startActivity(detalle);
            }

        });
    }

    @Override
    public int getItemCount() {

        return this.data.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }

    }
}
