package com.eoblitas.videojuegosfinales.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.eoblitas.videojuegosfinales.R;
import com.eoblitas.videojuegosfinales.entities.Libro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MyOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "bd.sqlite";
    private static final int DB_VERSION = 1;
    private static final String TABLA_LIBRO = "LIBRO";
    private SQLiteDatabase db;

    Context context;

    public MyOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
        db=this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String linea;
        String resultado = "";
        InputStream inputStream = context.getResources().openRawResource(R.raw.script_create_database);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        if(inputStream!=null){

            try {
                while ((linea = reader.readLine())!=null){

                    resultado = linea;

                    sqLiteDatabase.execSQL(resultado);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertarLibro(Libro libro){

        ContentValues cv = new ContentValues();
        cv.put("id", libro.getId());
        cv.put("titulo", libro.getTitulo());
        cv.put("resumen", libro.getResumen());
        cv.put("autor", libro.getAutor());
        cv.put("fecha_publicacion", libro.getFecha_publicacion());
        cv.put("tienda_1", libro.getTienda_1());
        cv.put("tienda_2", libro.getTienda_2());
        cv.put("tienda_3", libro.getTienda_3());
        cv.put("imagen", libro.getUrl_imagen());

        db.insert("Libro", null, cv);
    }

    public void actualizarLibro(Libro libro){

        ContentValues cv = new ContentValues();
        cv.put("titulo", libro.getTitulo());
        cv.put("resumen", libro.getResumen());
        cv.put("autor", libro.getAutor());
        cv.put("fecha_publicacion", libro.getFecha_publicacion());
        cv.put("tienda_1", libro.getTienda_1());
        cv.put("tienda_2", libro.getTienda_2());
        cv.put("tienda_3", libro.getTienda_3());
        cv.put("imagen", libro.getUrl_imagen());

        db.update("Libro", cv, "id= '" + libro.getId() + "'", null);
    }

    public Boolean consultarExistente(Integer id){

        Boolean condicion;

            Cursor c = db.rawQuery("select id from Libro where id = " + id  , null);
            if (c != null && c.getCount()>0) {
                condicion = true;
            }else{
                condicion = false;
            }
            
            c.close();
            return condicion;
    }

    @SuppressLint("Range")
    public ArrayList<Libro> obtenerLibros(){

        ArrayList<Libro> libros =new ArrayList<>();
        Cursor c = db.rawQuery("select id, titulo,resumen,autor,fecha_publicacion,tienda_1,tienda_2," +
                "tienda_3,imagen from Libro", null);
        if (c != null && c.getCount()>0) {
            c.moveToFirst();
            do {

                Libro libro =new Libro();

                libro.setId(c.getInt(c.getColumnIndex("id")));
                libro.setTitulo(c.getString(c.getColumnIndex("titulo")));
                libro.setResumen(c.getString(c.getColumnIndex("resumen")));
                libro.setAutor(c.getString(c.getColumnIndex("autor")));
                libro.setFecha_publicacion(c.getString(c.getColumnIndex("fecha_publicacion")));
                libro.setTienda_1(c.getString(c.getColumnIndex("tienda_1")));
                libro.setTienda_2(c.getString(c.getColumnIndex("tienda_2")));
                libro.setTienda_3(c.getString(c.getColumnIndex("tienda_3")));
                libro.setUrl_imagen(c.getString(c.getColumnIndex("imagen")));


                libros.add(libro);
            } while (c.moveToNext());
        }

        c.close();
        return libros;
    }
}
