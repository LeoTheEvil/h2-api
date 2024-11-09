package com.h2.h2_api.servicio;

import com.h2.h2_api.modelo.Libro;
import com.h2.h2_api.repositorio.RepositorioLibro;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@AllArgsConstructor
public class ServicioLibroImpl implements ServicioLibro{

    private final RepositorioLibro repositorioLibro;

    @Override
    public Libro guardarLibro(Libro libro) {
        return repositorioLibro.save(libro);
    }

    @Override
    public List<Libro> obtenerTodosLibros() {
        return repositorioLibro.findAll(firstPageWithTenElements);
    }

    @Override
    public Libro obtenerLibro(Long idLibro) {
        return repositorioLibro.findById(idLibro).orElseThrow(() -> {throw new ExcepcionNoEncuentraLibro();});
    }

    @Override
    public Libro libroAModificar(Long id, Libro libroAModificar) {
        Libro libroBuscado = repositorioLibro.findById(id).get();
        libroBuscado.setTitle(libroAModificar.getTitle());
        libroBuscado.setAuthor(libroAModificar.getAuthor());
        libroBuscado.setGenre(libroAModificar.getGenre());
        return repositorioLibro.save(libroBuscado);
    }

    @Override
    public boolean eliminarLibro(Long idLibro) {
        try {
            repositorioLibro.deleteById(idLibro);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}