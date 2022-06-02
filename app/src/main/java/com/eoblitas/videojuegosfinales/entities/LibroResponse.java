package com.eoblitas.videojuegosfinales.entities;

public class LibroResponse {

    private String codigo;
    private String resumen;
    private String url_imagen;
    private String titulo;
    private String autor;
    private String tienda_1;
    private String tienda_2;
    private String tienda_3;
    private String fecha_publicacion;
    private Integer id;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTienda_1() {
        return tienda_1;
    }

    public void setTienda_1(String tienda_1) {
        this.tienda_1 = tienda_1;
    }

    public String getTienda_2() {
        return tienda_2;
    }

    public void setTienda_2(String tienda_2) {
        this.tienda_2 = tienda_2;
    }

    public String getTienda_3() {
        return tienda_3;
    }

    public void setTienda_3(String tienda_3) {
        this.tienda_3 = tienda_3;
    }

    public String getFecha_publicacion() {
        return fecha_publicacion;
    }

    public void setFecha_publicacion(String fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
