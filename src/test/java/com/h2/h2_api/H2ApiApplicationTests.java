package com.h2.h2_api;

import com.h2.h2_api.controlador.ControladorLibro;
import com.h2.h2_api.modelo.Libro;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import io.restassured.module.jsv.JsonSchemaValidator.*;
import io.restassured.module.mockmvc.RestAssuredMockMvc.*;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class H2ApiApplicationTests {
	@LocalServerPort
	private int port=8081;

	@Test
	void debe_crear_un_libro_y_buscarlo_por_su_ID() {
		Libro libro = new Libro();
		libro.setTitle("Don Quijote de la Mancha");
		libro.setAuthor("Miguel de Cervantes");
		libro.setGenre("Comedia");
		Long id = given().port(port).body(libro).contentType(MediaType.APPLICATION_JSON.toString())
				.accept(MediaType.APPLICATION_JSON.toString()).when().post("/api/books").then().statusCode(201)
				.extract().jsonPath().getObject("id",Long.class);
		given().port(port).when().get("/api/books/"+id).then().body("id", equalTo(id));
	}
	@Test
	void debe_actualizar_un_libro() {
		Libro libro = new Libro();
		libro.setTitle("Don Quijote de la Mancha");
		libro.setAuthor("Miguel de Cervantes");
		libro.setGenre("Comedia");
		Long id = given().port(port).body(libro).contentType(MediaType.APPLICATION_JSON.toString())
				.accept(MediaType.APPLICATION_JSON.toString()).when().post("/api/books").then().statusCode(201)
				.extract().jsonPath().getObject("id",Long.class);
		libro.setAuthor("Miguel de Cervantes Saavedra");
		given().port(port).body(libro).contentType(MediaType.APPLICATION_JSON.toString())
				.accept(MediaType.APPLICATION_JSON.toString()).when().put("/api/books/"+id).then().statusCode(200);
		given().port(port).when().get("/api/books/"+id).then().body("author", equalTo("Miguel de Cervantes Saavedra"));
	}
	@Test
	void debe_borrar_un_libro() {
		Libro libro = new Libro();
		libro.setTitle("Don Quijote de la Mancha");
		libro.setAuthor("Miguel de Cervantes");
		libro.setGenre("Comedia");
		Long id = given().port(port).body(libro).contentType(MediaType.APPLICATION_JSON.toString())
				.accept(MediaType.APPLICATION_JSON.toString()).when().post("/api/books").then().statusCode(201)
				.extract().jsonPath().getObject("id",Long.class);
		given().port(port).when().delete("/api/books/"+id).then().statusCode(204);
		given().port(port).when().get("/api/books/"+id).then().statusCode(404);
	}
	@Test
	void debe_listar_todos_los_libros() {
		Libro libro1 = new Libro();
		libro1.setTitle("Don Quijote de la Mancha");
		libro1.setAuthor("Miguel de Cervantes");
		libro1.setGenre("Comedia");
		int id1 = given().port(port).body(libro1).contentType(MediaType.APPLICATION_JSON.toString())
				.accept(MediaType.APPLICATION_JSON.toString()).when().post("/api/books").then().statusCode(201)
				.extract().jsonPath().getObject("id",Integer.class);
		Libro libro2 = new Libro();
		libro2.setTitle("Cinco Semanas en Globo");
		libro2.setAuthor("Julio Verne");
		libro2.setGenre("Aventura");
		int id2 = given().port(port).body(libro2).contentType(MediaType.APPLICATION_JSON.toString())
				.accept(MediaType.APPLICATION_JSON.toString()).when().post("/api/books").then().statusCode(201)
				.extract().jsonPath().getObject("id",Integer.class);
		given().port(port).when().get("/api/books/").then().body("$",
				hasItem(
						allOf(
								hasEntry("id", id1)
						)
				)
		).body("$",
				hasItem(
						allOf(
								hasEntry("id", id2)
						)
				)
		);
	}
	@Test
	void debe_fallar_en_crear_un_libro() {
		Libro libro = new Libro();
		libro.setTitle("");
		libro.setAuthor("Miguel de Cervantes");
		libro.setGenre("Comedia");
		given().port(port).body(libro).contentType(MediaType.APPLICATION_JSON.toString())
				.accept(MediaType.APPLICATION_JSON.toString()).when().post("/api/books").then().statusCode(400);
	}
	@Test
	void debe_fallar_en_encontrar_un_libro() {
		given().port(port).when().get("/api/books/1").then().statusCode(404);
	}
	@Test
	void debe_fallar_en_actualizar_un_libro() {
		Libro libro = new Libro();
		libro.setTitle("Don Quijote de la Mancha");
		libro.setAuthor("Miguel de Cervantes");
		libro.setGenre("Comedia");
		Long id = given().port(port).body(libro).contentType(MediaType.APPLICATION_JSON.toString())
				.accept(MediaType.APPLICATION_JSON.toString()).when().post("/api/books").then().statusCode(201)
				.extract().jsonPath().getObject("id",Long.class);
		libro.setAuthor("");
		given().port(port).body(libro).contentType(MediaType.APPLICATION_JSON.toString())
				.accept(MediaType.APPLICATION_JSON.toString()).when().put("/api/books/"+id).then().statusCode(400);
	}
	@Test
	void debe_fallar_en_borrar_un_libro() {
		given().port(port).when().delete("/api/books/1").then().statusCode(404);
	}
}