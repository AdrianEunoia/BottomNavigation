package com.adrian.buttonnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.adrian.buttonnavigation.Adaptadores.AdaptadorRecyclerLigas;
import com.adrian.buttonnavigation.Fragments.FragmentAlert;
import com.adrian.buttonnavigation.Fragments.FragmentInfo;
import com.adrian.buttonnavigation.Fragments.FragmentMail;
import com.adrian.buttonnavigation.Utiles.Liga;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.MissingFormatArgumentException;

public class MainActivity extends AppCompatActivity implements AdaptadorRecyclerLigas.OnRecyclerListener {
    // Elementos
    FrameLayout idfragments;
    Toolbar idtoolbar;
    BottomNavigationView idbottomnav;
    NavigationView idnavigationormal;
    DrawerLayout iddrawer;
    private ActionBarDrawerToggle drawerToggle;
    // Elementos enfocados a json
    private String urlPeticion;
    private ArrayList<Liga> listaLigas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instancias();
        acciones();
    }
    // Instancias
    private void instancias() {
        idfragments = findViewById(R.id.idfragments);
        idtoolbar = findViewById(R.id.idtoolbar);
        idbottomnav = findViewById(R.id.idbottomnav);
        setSupportActionBar(idtoolbar);
        iddrawer = findViewById(R.id.iddrawer);
        idnavigationormal = findViewById(R.id.idnavigationnormal);
        drawerToggle = new ActionBarDrawerToggle(MainActivity.this,iddrawer,idtoolbar,R.string.open,R.string.close);
        iddrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setTitle("Titulo toolbar");
        // Json
        listaLigas = new ArrayList();
        urlPeticion = "https://www.thesportsdb.com/api/v1/json/1/all_leagues.php";
    }
    // Acciones
    private void acciones() {
        idbottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()){
                    case R.id.idopcion1:
                        fragmentTransaction.replace(R.id.idfragments, new FragmentMail());
                        break;
                    case R.id.idopcion2:
                        fragmentTransaction.replace(R.id.idfragments, new FragmentAlert());
                        break;
                    case R.id.idopcion3:
                        fragmentTransaction.replace(R.id.idfragments, new FragmentInfo());
                        break;
                }
                fragmentTransaction.commit();
                return false;
            }
        });
        idnavigationormal.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.idopcion1:
                        Toast.makeText(MainActivity.this, "Ligas", Toast.LENGTH_SHORT).show();
                        fragmentTransaction.replace(R.id.idfragments, new FragmentMail());
                        break;
                    case R.id.idopcion2:
                        Toast.makeText(MainActivity.this, "Española", Toast.LENGTH_SHORT).show();
                        fragmentTransaction.replace(R.id.idfragments, new FragmentAlert());
                        break;
                    case R.id.idopcion3:
                        Toast.makeText(MainActivity.this, "Inglesa", Toast.LENGTH_SHORT).show();
                        fragmentTransaction.replace(R.id.idfragments, new FragmentInfo());
                        break;
                }
                iddrawer.closeDrawers();
                fragmentTransaction.commit();
                return true;
            }
        });
    }
    @Override
    public void onLigaSelected(Liga liga) {
        System.out.println(liga.getNombre());
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.idfragments, FragmentAlert.newInstance(liga));
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}
