# Autoevaluacion AD05

## Tema y regla de borrado
La aplicacion gestiona una biblioteca personal formada por libros y capitulos. Cada capitulo pertenece a un unico libro. Al eliminar un libro se eliminan sus capitulos mediante cascada JPA porque no tienen sentido sin el libro. El borrado de un capitulo no afecta al libro.

## 1. Configuracion y acceso a Oracle - 1/1
El proyecto Maven arranca con Spring Boot y se conecta al servicio XEPDB1 mediante el esquema AD05_APP. La clave se obtiene de la variable de entorno ORACLE_PASSWORD y no se publica. Hibernate utiliza ddl-auto=update para conservar datos.
Evidencia: captura del arranque sin errores y listado de libros despues de reiniciar.

## 2. Entidades y relacion - 1,5/1,5
Libro y Capitulo estan mapeados con JPA. La relacion es bidireccional: Libro usa @OneToMany(mappedBy="libro") y Capitulo usa @ManyToOne con LIBRO_ID.
Evidencia: fragmentos de ambas entidades y prueba de un capitulo asociado.

## 3. Gestion principal - 1,5/1,5
La web permite crear, listar y editar libros. La edicion conserva el identificador y actualiza la fila existente.
Evidencia: formulario antes y listado despues de editar.

## 4. Gestion dependiente - 2/2
La web permite crear, listar y editar capitulos; el listado muestra el titulo del libro. El selector se genera desde los libros existentes y permite cambiar la relacion. El servidor verifica que el libro exista.
Evidencia: selector, listado y cambio de libro.

## 5. Borrado y cascada - 1,5/1,5
Los borrados requieren confirmacion y se ejecutan por POST. LibroService elimina dentro de una transaccion y la cascada JPA elimina los capitulos. El borrado de un capitulo conserva el libro.
Evidencia: confirmacion y listados antes/despues.

## 6. MVC, Thymeleaf, validacion y presentacion - 1,5/1,5
La aplicacion separa controladores, servicios y repositorios, usa inyeccion por constructor, plantillas Thymeleaf, Bootstrap y validacion del servidor. Los errores conservan el formulario.
Evidencia: error de validacion visible y navegacion.

## 7. Proceso, autoevaluacion e IA - 1/1
He trabajado por apartados, comprobando cada funcionalidad con datos propios. Utilice IA como apoyo para estructurar el proyecto, revisar el mapeo bidireccional y contrastar el borrado transaccional. Acepte la separacion por capas y la configuracion sin credenciales; adapte el tema, nombres, campos, validaciones y mensajes. Verifique el resultado ejecutando el proyecto, realizando altas, ediciones, cambios de relacion, reinicios y ambos borrados. Descarte cualquier solucion que conectara como SYSTEM o borrara dependientes manualmente.

## Puntuacion propuesta
10/10, condicionada a adjuntar las capturas propias indicadas y demostrar el funcionamiento real.
