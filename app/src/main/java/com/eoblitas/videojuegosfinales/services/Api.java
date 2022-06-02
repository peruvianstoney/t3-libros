package com.eoblitas.videojuegosfinales.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    public static Retrofit getRetrofit() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://6298a07ef2decf5bb7477e26.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;

    }
}
