package com.adrian.buttonnavigation.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adrian.buttonnavigation.R;
import com.adrian.buttonnavigation.Utiles.Liga;

import java.util.ArrayList;

public class AdaptadorReyclerLigaSola extends RecyclerView.Adapter<AdaptadorReyclerLigaSola.MyHolder>{
    ArrayList<Liga> listaLigas;
    Context context;
    // Constructor
    public AdaptadorReyclerLigaSola(Context context) {
        this.listaLigas = new ArrayList<>();
        this.context = context;
    }
    // Create view
    @NonNull
    @Override
    public AdaptadorReyclerLigaSola.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_recycler_sola,parent,false);
        return new AdaptadorReyclerLigaSola.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        final Liga liga = listaLigas.get(position); // Al loro con el final.
        holder.getNombreLiga().setText(liga.getNombre());
        holder.getNombreLiga().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO cualdo pulso ejecuto la interfaz del callback para pasar al main el id de la
                // liga selaccionada
                Toast.makeText(context,String.valueOf(liga.getId()), Toast.LENGTH_LONG).show();
            }
        });
    }
    // Tamaño adaptador
    @Override
    public int getItemCount() {
        return listaLigas.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView nombreLiga;
        public MyHolder(@NonNull View itemView) {
            // Super importante
            super(itemView);
            nombreLiga = itemView.findViewById(R.id.nombre_liga);
        }
        public TextView getNombreLiga() {
            return nombreLiga;
        }
    }
    public void agregarElemento(Liga liga){
        listaLigas.add(liga);
        notifyDataSetChanged();
    }
}
