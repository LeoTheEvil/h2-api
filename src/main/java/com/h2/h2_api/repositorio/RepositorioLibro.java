package com.h2.h2_api.repositorio;

import com.h2.h2_api.modelo.Libro;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioLibro extends JpaRepository<Libro,Long> {

}