package com.eoblitas.videojuegosfinales;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.eoblitas.videojuegosfinales.adapter.LibroAdapter;
import com.eoblitas.videojuegosfinales.database.MyOpenHelper;
import com.eoblitas.videojuegosfinales.databinding.ActivityListadoLibrosBinding;
import com.eoblitas.videojuegosfinales.databinding.ActivityMainBinding;

public class ListadoLibrosActivity extends AppCompatActivity {

    ActivityListadoLibrosBinding binding;
    MyOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListadoLibrosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db=new MyOpenHelper(this);

        LibroAdapter adapter = new LibroAdapter(db.obtenerLibros());
        binding.rvLibros.setAdapter(adapter);

    }
}