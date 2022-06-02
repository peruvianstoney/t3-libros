package com.eoblitas.videojuegosfinales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

public class DetalleActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        Intent intent = getIntent();

        Integer id = intent.getIntExtra("ID",-1);
        String titulo = intent.getStringExtra("TITULO");
        String resumen = intent.getStringExtra("RESUMEN");
        String autor = intent.getStringExtra("AUTOR");
        String fecha_publicacion = intent.getStringExtra("FECHA_PUBLICACION");
        String tienda_uno = intent.getStringExtra("TIENDA_UNO");
        String tienda_dos = intent.getStringExtra("TIENDA_DOS");
        String tienda_tres = intent.getStringExtra("TIENDA_TRES");
        String imagen = intent.getStringExtra("IMAGEN");

        TextView tvId = findViewById(R.id.tvId);
        TextView tvTitulo = findViewById(R.id.tvTitulo);
        TextView tvAutor = findViewById(R.id.tvAutor);
        TextView tvResumen = findViewById(R.id.tvResumen);
        TextView tvFecha = findViewById(R.id.tvFecha);
        TextView tvTienda1 = findViewById(R.id.tvTienda1);
        TextView tvTienda2 = findViewById(R.id.tvTienda2);
        TextView tvTienda3 = findViewById(R.id.tvTienda3);
        ImageView imgLibroDetalle = findViewById(R.id.imgLibroDetalle);

        tvId.setText(""+id);
        tvTitulo.setText(titulo);
        tvAutor.setText(autor);
        tvResumen.setText(resumen);
        tvFecha.setText(fecha_publicacion);
        tvTienda1.setText(tienda_uno);
        tvTienda2.setText(tienda_dos);
        tvTienda3.setText(tienda_tres);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        String ruta = "https://6298a07ef2decf5bb7477e26.mockapi.io"+imagen;
        Picasso.get().load(ruta).resize (250, 250).into(imgLibroDetalle);

        /*btnUbicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ubicacion = new Intent(getApplicationContext(),MapsActivity.class);
                ubicacion.putExtra("Latitud",latitud);
                ubicacion.putExtra("Longitud",longitud);

                startActivity(ubicacion);
            }
        });*/

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-7.1515561,-78.5074503);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Salaverry Plaza"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}