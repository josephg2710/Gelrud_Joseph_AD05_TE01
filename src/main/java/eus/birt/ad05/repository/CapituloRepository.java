package eus.birt.ad05.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import eus.birt.ad05.model.Capitulo;

public interface CapituloRepository extends JpaRepository<Capitulo, Long> {

    @Override
    @EntityGraph(attributePaths = "libro")
    List<Capitulo> findAll();

    @Override
    @EntityGraph(attributePaths = "libro")
    Optional<Capitulo> findById(Long id);

    boolean existsByLibro_IdAndNumeroAndIdNot(
            Long libroId,
            Integer numero,
            Long id
    );
}