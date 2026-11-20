package eus.birt.ad05.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(
    name = "CAPITULOS",
    uniqueConstraints = @UniqueConstraint(
        name = "UK_CAPITULO_LIBRO_NUM",
        columnNames = {"LIBRO_ID", "NUMERO"}
    )
)
public class Capitulo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "capitulo_seq")
    @SequenceGenerator(name = "capitulo_seq", sequenceName = "CAPITULO_SEQ", allocationSize = 1)
    private Long id;

    @NotBlank(message = "El titulo es obligatorio")
    @Size(max = 120)
    private String titulo;

    @NotNull(message = "El numero es obligatorio")
    @Min(value = 1, message = "El numero debe ser mayor que 0")
    private Integer numero;

    @NotBlank(message = "El resumen es obligatorio")
    @Size(max = 500)
    @Column(length = 500)
    private String resumen;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "LIBRO_ID", nullable = false)
    private Libro libro;

    @Transient
    @NotNull(message = "Debes seleccionar un libro")
    private Long libroId;

    public Capitulo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Long getLibroId() {
        return libroId;
    }

    public void setLibroId(Long libroId) {
        this.libroId = libroId;
    }
}