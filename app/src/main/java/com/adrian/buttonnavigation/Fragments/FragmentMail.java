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

public class FragmentMail extends Fragment {
    // Elementos
    private RecyclerView lista;
    private ArrayList<Liga> listaLigas;
    AdaptadorRecyclerLigas adaptadorRecyclerLigas;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        adaptadorRecyclerLigas = new AdaptadorRecyclerLigas(getContext());
        listaLigas = new ArrayList();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Cargar JSON
        JsonObjectRequest peticionJson = new JsonObjectRequest(Request.Method.GET, "https://www.thesportsdb.com/api/v1/json/1/all_leagues.php", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Log.v("volley",response.toString());
                try {
                    JSONArray jsonArray = response.getJSONArray("leagues");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String deporte = jsonObject.getString("strSport");
                        String nombre = jsonObject.getString("strLeague");
                        int id = jsonObject.getInt("idLeague");
                        if (deporte.toLowerCase().equals("soccer") && deporte.toLowerCase().length() > 0) {
                            //Log.v("volley",nombre);
                            Liga liga = new Liga(id, nombre);
                            adaptadorRecyclerLigas.agregarElemento(liga);
                        }

                        Log.v("volley", String.valueOf(listaLigas.size()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("volley", "Error en la conexion asdfghjklkjhgfdsasdfghj");
            }
        });
        Volley.newRequestQueue(getContext()).add(peticionJson);
        lista.setAdapter(adaptadorRecyclerLigas);
        lista.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_mail, container, false);
        lista = view.findViewById(R.id.idrecyclerliga);
        return view;
    }
}