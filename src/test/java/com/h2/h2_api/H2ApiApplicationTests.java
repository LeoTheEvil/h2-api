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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@SpringBootTest
class H2ApiApplicationTests {

	@Test
	void debe_crear_un_libro() {
		Libro libro = new Libro();
		libro.setId(1L);
		libro.setTitle("Don Quijote de la Mancha");
		libro.setAuthor("Miguel de Cervantes");
		libro.setGenre("Comedia");

	}
	@Test
	void debe_buscar_un_libro_por_su_ID() {
		Libro libro = new Libro();
		libro.setId(1L);
		libro.setTitle("Don Quijote de la Mancha");
		libro.setAuthor("Miguel de Cervantes");
		libro.setGenre("Comedia");
		given().when().get("/api/books").then().body("libro.id", equalTo(1L));
	}
	@Test
	void debe_actualizar_un_libro() {

	}
	@Test
	void debe_borrar_un_libro() {

	}
	@Test
	void debe_listar_todos_los_libros() {

	}
	@Test
	void debe_fallar_en_crear_un_libro() {

	}
	@Test
	void debe_fallar_en_encontrar_un_libro() {

	}
	@Test
	void debe_fallar_en_borrar_un_libro() {

	}
}