# Desafio: Gestion de libros

En este desafio nos planteamos realizar una aplicacion para gestionar libros basada en springboot con una base de datos
en memoria (h2)

La misma debe incluir los siguientes metodos:

Crear un libro:
    Método HTTP: POST
    URL: /api/books
    Cuerpo de la petición: JSON con los campos title, author y genre
    Respuesta: JSON con el libro creado, incluyendo el ID generado
Obtener todos los libros:
    Método HTTP: GET
    URL: /api/books
    Parametros de paginacion:
        offset (numero mayor o igual a 0, por defecto 0)
            Cual pagina de la lista se desea ver.
        size (numero mayor a 0, por defecto 10)
            Cuantos libros se desean ver por pagina.
    Respuesta: JSON con una lista de todos los libros
Obtener un libro por ID:
    Método HTTP: GET
    URL: /api/books/{id}
    Respuesta: JSON con el libro correspondiente al ID
Actualizar un libro:
    Método HTTP: PUT
    URL: /api/books/{id}
    Cuerpo de la petición: JSON con los nuevos datos del libro
    Respuesta: JSON con el libro actualizado
Eliminar un libro:
    Método HTTP: DELETE
    URL: /api/books/{id}
    Respuesta: HTTP 204 No Content

## Requerimientos

* Java 1.7
* Git
* IDE a eleccion
* Maven

## Clonado del repositorio

```shell script
git clone git@github.com:LeoTheEvil/h2-api.git
```

## Build

```shell script
mvn clean install
```

## Ejecutar

```shell script
java 
```

## API

Ver documentacion de [Swagger](http://localhost:8081/swagger-ui/index.html#/)

## Entidades

### Libro

Ejemplo:
```json
  {
    "id": 1,
    "title": "Cinco semanas en globo",
    "author": "Julio Verne",
    "genre": "Aventura"
  }
```

Un libro consiste de un ID, un titulo, un autor y un genero.