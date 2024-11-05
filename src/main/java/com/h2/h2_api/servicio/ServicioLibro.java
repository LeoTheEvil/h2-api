package com.h2.h2_api.servicio;

import com.h2.h2_api.modelo.Libro;

import java.util.Optional;

public interface ServicioLibro {
    Libro guardarLibro(Libro libro);
 //   Libro obtenerTodosLibros();
    Libro obtenerLibro(Long idLibro);
    Libro libroAModificar(Long id, Libro libroAModificar);
    boolean eliminarLibro(Long idLibro);
}