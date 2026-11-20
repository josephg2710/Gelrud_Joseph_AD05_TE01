package eus.birt.ad05.service;
import eus.birt.ad05.model.Libro; import eus.birt.ad05.repository.LibroRepository; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.util.*;
@Service public class LibroService {
 private final LibroRepository repo; public LibroService(LibroRepository repo){this.repo=repo;}
 @Transactional(readOnly=true) public List<Libro> listar(){return repo.findAll();}
 @Transactional(readOnly=true) public Libro buscar(Long id){return repo.findById(id).orElseThrow(()->new RecursoNoEncontradoException("Libro no encontrado"));}
 @Transactional public Libro guardar(Libro libro){return repo.save(libro);}
 @Transactional public Libro actualizar(Long id, Libro datos){Libro l=buscar(id);l.setTitulo(datos.getTitulo());l.setAutor(datos.getAutor());l.setGenero(datos.getGenero());l.setDescripcion(datos.getDescripcion());return repo.save(l);}
 @Transactional public void eliminar(Long id){repo.delete(buscar(id));}
 public boolean hayLibros(){return repo.count()>0;}
}
