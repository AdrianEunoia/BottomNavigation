package com.adrian.buttonnavigation.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adrian.buttonnavigation.Adaptadores.AdaptadorRecyclerLigas;
import com.adrian.buttonnavigation.Adaptadores.AdaptadorReyclerLigaSola;
import com.adrian.buttonnavigation.R;
import com.adrian.buttonnavigation.Utiles.Liga;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentAlert extends Fragment {
    private static final String TAG_ARG1 = "ligaPasada";
    // Elementos
    private RecyclerView lista;
    AdaptadorReyclerLigaSola adaptadorReyclerLigaSola;
    Liga ligaPasada;
    // Attachment
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        adaptadorReyclerLigaSola = new AdaptadorReyclerLigaSola(getContext());
        ligaPasada = (Liga) this.getArguments().get(TAG_ARG1);
    }
    // Instancia (Nueva)
    public static FragmentAlert newInstance(Liga ligaPasada){
        FragmentAlert fragmentDetalle = new FragmentAlert();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TAG_ARG1, ligaPasada);
        fragmentDetalle.setArguments(bundle);
        return fragmentDetalle;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adaptadorReyclerLigaSola.agregarElemento(ligaPasada);
        lista.setAdapter(adaptadorReyclerLigaSola);
        lista.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_alert, container, false);
        lista = view.findViewById(R.id.idrecyclerindividual);
        return view;
    }
}