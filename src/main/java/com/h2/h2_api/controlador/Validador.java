package com.h2.h2_api.controlador;

import com.h2.h2_api.modelo.Libro;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Validador {
    public void validar(Libro libro) {
        if (libro.getTitle().isBlank()) {
            throw new ParametroIncorrecto("El titulo no puede ser vacio.");
        }
        if (libro.getAuthor().isBlank()) {
            throw new ParametroIncorrecto("El autor no puede ser vacio.");
        }
        if (libro.getGenre().isBlank()) {
            throw new ParametroIncorrecto("El genero no puede ser vacio.");
        }
    }
}
