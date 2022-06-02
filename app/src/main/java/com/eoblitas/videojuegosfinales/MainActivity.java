package com.eoblitas.videojuegosfinales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.eoblitas.videojuegosfinales.database.MyOpenHelper;
import com.eoblitas.videojuegosfinales.databinding.ActivityMainBinding;
import com.eoblitas.videojuegosfinales.entities.Libro;
import com.eoblitas.videojuegosfinales.services.Api;
import com.eoblitas.videojuegosfinales.services.LibroService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    MyOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db=new MyOpenHelper(this);

        binding.btnSincronizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sincronizar();
            }
        });

        binding.btnConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),ListadoLibrosActivity.class);
                startActivity(intent);
            }
        });

        binding.btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(),RegistroActivity.class);
                startActivity(intent);
            }
        });
    }

    private void sincronizar() {

        LibroService service = Api.getRetrofit().create(LibroService.class);
        service.obtenerLibros().enqueue(new Callback<List<Libro>>() {
            @Override
            public void onResponse(Call<List<Libro>> call, Response<List<Libro>> response) {

                if(response.isSuccessful()){

                    List<Libro> libros = response.body();

                    for (Libro libro:libros) {

                        Boolean resultado = db.consultarExistente(libro.getId());

                        if(resultado){
                            db.actualizarLibro(libro);
                        }else{
                            db.insertarLibro(libro);
                        }


                    }

                }else{
                    Toast.makeText(getBaseContext(),response.message(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Libro>> call, Throwable t) {
                Toast.makeText(getBaseContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }
}