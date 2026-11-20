package eus.birt.ad05.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.*;
@Entity @Table(name="LIBROS")
public class Libro {
 @Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="libro_seq")
 @SequenceGenerator(name="libro_seq", sequenceName="LIBRO_SEQ", allocationSize=1)
 private Long id;
 @NotBlank(message="El titulo es obligatorio") @Size(max=120) private String titulo;
 @NotBlank(message="El autor es obligatorio") @Size(max=100) private String autor;
 @NotBlank(message="El genero es obligatorio") @Size(max=60) private String genero;
 @NotBlank(message="La descripcion es obligatoria") @Size(max=500) @Column(length=500) private String descripcion;
 @OneToMany(mappedBy="libro", cascade=CascadeType.ALL, orphanRemoval=true)
 @OrderBy("numero ASC") private List<Capitulo> capitulos=new ArrayList<>();
 public Libro() {}
 public Long getId(){return id;} public void setId(Long id){this.id=id;}
 public String getTitulo(){return titulo;} public void setTitulo(String v){titulo=v;}
 public String getAutor(){return autor;} public void setAutor(String v){autor=v;}
 public String getGenero(){return genero;} public void setGenero(String v){genero=v;}
 public String getDescripcion(){return descripcion;} public void setDescripcion(String v){descripcion=v;}
 public List<Capitulo> getCapitulos(){return capitulos;} public void setCapitulos(List<Capitulo> v){capitulos=v;}
 public void addCapitulo(Capitulo c){capitulos.add(c);c.setLibro(this);}
 public void removeCapitulo(Capitulo c){capitulos.remove(c);c.setLibro(null);}
}
