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

public class AdaptadorRecyclerLigas extends RecyclerView.Adapter<AdaptadorRecyclerLigas.MyHolder> {
    ArrayList<Liga> listaLigas;
    Context context;
    private OnRecyclerListener listener;
    // Constructor
    public AdaptadorRecyclerLigas(Context context) {
        this.listaLigas = new ArrayList<>();
        this.context = context;
        try{
            listener = (OnRecyclerListener) context;
        }
        catch (ClassCastException e){
            e.printStackTrace();
        }
    }
    // Create view
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_recycler,parent,false);
        return new MyHolder(view);
    }
    // Acciones adaptador
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
                listener.onLigaSelected(liga);

            }
        });
    }
    // Tamaño adaptador
    @Override
    public int getItemCount() {
        return listaLigas.size();
    }
    // Clase adaptador
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
    // Añadir elementos al recycler desde el fragment de ligas
    public void agregarElemento(Liga liga){
        listaLigas.add(liga);
        notifyDataSetChanged();
    }
    public interface OnRecyclerListener{
        void onLigaSelected(Liga liga);
    }
}
