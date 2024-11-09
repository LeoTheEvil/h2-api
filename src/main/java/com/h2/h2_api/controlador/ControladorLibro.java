package com.h2.h2_api.controlador;

import com.h2.h2_api.modelo.Libro;
import com.h2.h2_api.servicio.ExcepcionNoEncuentraLibro;
import com.h2.h2_api.servicio.ServicioLibro;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class ControladorLibro {

    private final ServicioLibro servicioLibro;
    private final Validador validador;
    @PostMapping
    public ResponseEntity guardarLibro(@RequestBody Libro libro) {
        try {validador.validar(libro);
            return new ResponseEntity(servicioLibro.guardarLibro(libro), HttpStatus.CREATED);
        } catch(ParametroIncorrecto e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/")
    public ResponseEntity obtenerTodosLibros(@RequestParam int offset, @RequestParam int size) {
        if (size==0) {size=10;}
        try {
            return new ResponseEntity(servicioLibro.obtenerTodosLibros(1, 10), HttpStatus.OK);
        } catch (ExcepcionNoEncuentraLibro e) {
            return new ResponseEntity("No hay ningun libro en la lista.",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity obtenerLibro(@PathVariable("id") Long idLibro) {
        try {
            return new ResponseEntity(servicioLibro.obtenerLibro(idLibro), HttpStatus.OK);
        } catch (ExcepcionNoEncuentraLibro e) {
            return new ResponseEntity("El libro "+idLibro+" no fue encontrado.",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity modificarLibro(@PathVariable("id") Long idLibro, @RequestBody Libro libro) {
        try {validador.validar(libro);
            return new ResponseEntity(servicioLibro.libroAModificar(idLibro,libro), HttpStatus.OK);
        } catch(ParametroIncorrecto e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarLibro(@PathVariable("id") Long idLibro) {
        boolean respuesta = servicioLibro.eliminarLibro(idLibro);
        if (respuesta == true) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity("El libro "+idLibro+" no fue encontrado.",HttpStatus.NOT_FOUND);
        }
    }
}