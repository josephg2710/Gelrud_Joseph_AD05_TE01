# AD05 - Biblioteca personal

Aplicacion web Maven con Spring Boot, Spring MVC, Spring Data JPA, Thymeleaf, Bootstrap y Oracle XE 21c.

## Modelo
- `Libro`: entidad principal.
- `Capitulo`: entidad dependiente.
- Relacion bidireccional 1:N. Al eliminar un libro se eliminan sus capitulos mediante cascada JPA. Eliminar un capitulo no elimina el libro.

## Versiones
- Java 17
- Spring Boot 4.1.2
- Oracle JDBC `ojdbc17` 23.26.4.0.0
- Oracle XE 21c, servicio XEPDB1

## Preparacion
1. En SQL Developer, conectarse como administrador al servicio `XEPDB1`, nunca a `CDB$ROOT`.
2. Editar y ejecutar una sola vez `preparar_usuario_oracle.sql`. Sustituir la clave de ejemplo y no publicarla.
3. Definir la variable de entorno `ORACLE_PASSWORD` con la clave real.

Windows PowerShell:
```powershell
$env:ORACLE_PASSWORD="TU_CLAVE_LOCAL"
.\mvnw spring-boot:run
```

Si no se incluye Maven Wrapper, usar Maven instalado:
```powershell
$env:ORACLE_PASSWORD="TU_CLAVE_LOCAL"
mvn spring-boot:run
```

Abrir `http://localhost:8080`.

Hibernate usa `spring.jpa.hibernate.ddl-auto=update`, por lo que crea y actualiza las tablas sin borrar los datos entre ejecuciones.

## Comprobaciones recomendadas
1. Crear un libro y reiniciar: debe conservarse.
2. Editarlo: debe cambiar la misma fila.
3. Crear un capitulo seleccionando un libro.
4. Editar el capitulo y cambiar su libro.
5. Eliminar un capitulo: el libro se conserva.
6. Eliminar un libro con capitulos: se eliminan por cascada.
7. Probar campos vacios, numero menor que 1 e identificadores inexistentes.

## Seguridad del repositorio
No subir claves, `.env`, `application-local.properties`, datos personales, ni `target/`.
