package com.eoblitas.videojuegosfinales.services;

import com.eoblitas.videojuegosfinales.entities.Libro;
import com.eoblitas.videojuegosfinales.entities.LibroRequest;
import com.eoblitas.videojuegosfinales.entities.LibroResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LibroService {

    @GET("libros")
    Call<List<Libro>> obtenerLibros();

    @POST("libros")
    Call<LibroResponse> grabarLibro(@Body LibroRequest libroRequest);

}
