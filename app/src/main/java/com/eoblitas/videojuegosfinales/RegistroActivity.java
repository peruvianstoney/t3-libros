package com.eoblitas.videojuegosfinales;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.eoblitas.videojuegosfinales.databinding.ActivityRegistroBinding;
import com.eoblitas.videojuegosfinales.entities.LibroRequest;
import com.eoblitas.videojuegosfinales.entities.LibroResponse;
import com.eoblitas.videojuegosfinales.services.Api;
import com.eoblitas.videojuegosfinales.services.LibroService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity {

    ActivityRegistroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LibroRequest request = new LibroRequest();
                request.setTitulo(binding.edtTituloGrabar.getText().toString());
                request.setResumen(binding.edtResumenGrabar.getText().toString());
                request.setAutor(binding.edtAutorGrabar.getText().toString());
                request.setFecha_publicacion(binding.edtFechaGrabar.getText().toString());
                request.setTienda_1(binding.edtTienda1Grabar.getText().toString());
                request.setTienda_2(binding.edtTienda2Grabar.getText().toString());
                request.setTienda_3(binding.edtTienda3Grabar.getText().toString());

                LibroService service = Api.getRetrofit().create(LibroService.class);
                service.grabarLibro(request).enqueue(new Callback<LibroResponse>() {
                    @Override
                    public void onResponse(Call<LibroResponse> call, Response<LibroResponse> response) {

                        if(response.isSuccessful()){
                            Toast.makeText(getBaseContext(),"Libro Grabado correctamente",Toast.LENGTH_LONG).show();
                            onBackPressed();
                        }
                        else{
                            Toast.makeText(getBaseContext(),response.message(),Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LibroResponse> call, Throwable t) {
                        Toast.makeText(getBaseContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}