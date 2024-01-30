package com.dem.propeliculas.Interfas;

import com.dem.propeliculas.Modelo.Resultado;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfasPelicula {
    @GET("popular")
    Call<Resultado> obtenerDatos(@Query("api_key")String api_key, @Query("page")int page);



}
