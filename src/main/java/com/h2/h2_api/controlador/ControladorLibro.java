package com.h2.h2_api.controlador;

import com.h2.h2_api.modelo.Libro;
import com.h2.h2_api.servicio.ServicioLibro;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/libros")
@RequiredArgsConstructor
public class ControladorLibro {

    private final ServicioLibro servicioLibro;
    @GetMapping("/")
    public ResponseEntity health() {
        return new ResponseEntity(HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity guardarLibro(@RequestBody Libro libro) {
        return new ResponseEntity(servicioLibro.guardarLibro(libro), HttpStatus.CREATED);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity obtenerTodosLibros() {
//        return new ResponseEntity(servicioLibro.obtenerTodosLibros(), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity obtenerLibro(@PathVariable("id") Long idLibro) {
        return new ResponseEntity(servicioLibro.obtenerLibro(idLibro), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity modificarLibro(@PathVariable("id") Long idLibro, @RequestBody Libro libro) {
        return new ResponseEntity(servicioLibro.libroAModificar(idLibro,libro), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarLibro(@PathVariable("id") Long idLibro) {
        boolean respuesta = servicioLibro.eliminarLibro(idLibro);
        if (respuesta == true) {
            return new ResponseEntity(HttpStatus.OK);
        }
        else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}