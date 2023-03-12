import static io.restassured.RestAssured.given;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;

public class ConsultBreeds {

	
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "https://catfact.ninja";
	}

	@Test
	public void consultBreedError() {
		given()
			.log().all()
			.pathParam("limit","-1")
		.when()
		.get("/breeds?limit={limit}")
		.then()
		.log().all()
		.statusCode(404)
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("error.json"));
	}

	@Test
	public void consultOneBreed() {
		given()
			.log().all()
			.pathParam("limit","1")
		.when()
		.get("/breeds?limit={limit}")
		.then()
		.log().all()
		.statusCode(200)
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("oneBreed.json"));
		
		
	}
	
	@Test
	public void consultAllBreeds() {
		given()
			.log().all()
			.pathParam("limit","98")
		.when()
		.get("/breeds?limit={limit}")
		.then()
		.log().all()
		.statusCode(200)
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("allBreeds.json"));
	}	
	
	@Test
	public void consultZeroBreed() {
		given()
			.log().all()
			.pathParam("limit","0")
		.when()
		.get("/breeds?limit={limit}")
		.then()
		.log().all()
		.statusCode(200)
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("zeroBreed.json"));
	}	
}