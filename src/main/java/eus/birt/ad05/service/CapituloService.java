package eus.birt.ad05.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eus.birt.ad05.model.Capitulo;
import eus.birt.ad05.model.Libro;
import eus.birt.ad05.repository.CapituloRepository;

@Service
public class CapituloService {

    private final CapituloRepository repo;
    private final LibroService libros;

    public CapituloService(CapituloRepository repo, LibroService libros) {
        this.repo = repo;
        this.libros = libros;
    }

    @Transactional(readOnly = true)
    public List<Capitulo> listar() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Capitulo buscar(Long id) {
        Capitulo capitulo = repo.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Capitulo no encontrado"));

        capitulo.setLibroId(capitulo.getLibro().getId());

        return capitulo;
    }

    @Transactional
    public Capitulo guardar(Capitulo capitulo) {

        Libro libro = libros.buscar(capitulo.getLibroId());

        capitulo.setLibro(libro);

        validarNumero(capitulo);

        return repo.save(capitulo);
    }

    @Transactional
    public Capitulo actualizar(Long id, Capitulo datos) {

        Capitulo capitulo = buscar(id);

        Libro libro = libros.buscar(datos.getLibroId());

        capitulo.setTitulo(datos.getTitulo());
        capitulo.setNumero(datos.getNumero());
        capitulo.setResumen(datos.getResumen());
        capitulo.setLibro(libro);
        capitulo.setLibroId(libro.getId());

        validarNumero(capitulo);

        return repo.save(capitulo);
    }

    @Transactional
    public void eliminar(Long id) {

        repo.delete(buscar(id));
    }

    private void validarNumero(Capitulo capitulo) {

        long idActual =
                capitulo.getId() == null ? -1L : capitulo.getId();

        if (repo.existsByLibro_IdAndNumeroAndIdNot(
                capitulo.getLibro().getId(),
                capitulo.getNumero(),
                idActual)) {

            throw new IllegalArgumentException(
                    "Ese libro ya tiene un capitulo con el mismo numero");
        }
    }
}