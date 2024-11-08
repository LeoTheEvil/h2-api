package com.h2.h2_api.servicio;

import com.h2.h2_api.modelo.Libro;

import java.util.List;

public interface ServicioLibro {
    Libro guardarLibro(Libro libro);
    List<Libro> obtenerTodosLibros();
    Libro obtenerLibro(Long idLibro);
    Libro libroAModificar(Long id, Libro libroAModificar);
    boolean eliminarLibro(Long idLibro);
}