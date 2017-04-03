package com.example.humor.seletiene_app;

/**
 * Created by humor on 03/04/2017.
 */

public class Entrada_Libros {
    String libro, autor, descripcion;

    public Entrada_Libros(String libro, String autor, String descripcion) {
        this.libro = libro;
        this.autor = autor;
        this.descripcion = descripcion;
    }

    public String getLibro() {
        return libro;
    }

    public String getAutor() {
        return autor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setLibro(String libro) {
        this.libro = libro;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
