package eus.birt.ad05.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import eus.birt.ad05.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Override
    @EntityGraph(attributePaths = "capitulos")
    List<Libro> findAll();
}