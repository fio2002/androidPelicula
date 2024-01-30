package com.dem.propeliculas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.dem.propeliculas.Adaptador.AdaptadorPelicula;
import com.dem.propeliculas.Interfas.InterfasPelicula;
import com.dem.propeliculas.Modelo.Pelicula;
import com.dem.propeliculas.Modelo.Resultado;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView listado;
    private Retrofit rfit;
    private boolean cargar;
    private AdaptadorPelicula ad;
    private int page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listado=findViewById(R.id.Listado);

        rfit= new Retrofit.Builder().baseUrl("https://api.themoviedb.org/3/").addConverterFactory(
                GsonConverterFactory.create()).build();
        cargar=true;

        // initialize the adapter and set it to the RecyclerView

        // add the scroll listener to the RecyclerViewg

        // load the initial data
        page = 1;
        obtener(page);
    }

    private void obtener(int page) {
        InterfasPelicula s=rfit.create(InterfasPelicula.class);
        Call<Resultado>respuesta=s.obtenerDatos(String.valueOf(page),20);
        respuesta.enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                cargar=true;
                if(response.isSuccessful()){
                    Resultado res=response.body();
                    ArrayList<Pelicula> lista=res.getResults();
                    ad.adicionarRegistro(lista);
                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
                cargar=true;
            }
        });
    }
}