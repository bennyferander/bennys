package se.ferander.digg;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerResourceTest {
    @Inject
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        RestAssured.delete("/digg/user/all"); // Om du har en "clear all"-endpoint
    }

    @AfterAll
    void afterTests(){
        //Provisionera kunder i repositoryt
        customerRepository.seedCustomers();
    }

    @Test
    void testPostAndGetCustomers() {
        // 1. Lägg till 10 kunder med POST
        for (int i = 1; i <= 10; i++) {
            String json = String.format(
                    "{\"name\":\"Kund %d\", \"address\":\"Adress %d\", \"email\":\"kund%d@example.com\", \"telephone\":\"070000000%d\"}",
                    i, i, i, i
            );

            given()
                    .contentType("application/json")
                    .body(json)
                    .when()
                    .post("/digg/user")
                    .then()
                    .statusCode(201)
                    .body("name", equalTo("Kund " + i))
                    .body("address", equalTo("Adress " + i))
                    .body("email", equalTo("kund" + i + "@example.com"))
                    .body("telephone", equalTo("070000000" + i));
        }

        // 2. Hämta första sidan med GET (page=0, size=5)
        given()
                .queryParam("page", 0)
                .queryParam("size", 5)
                .when()
                .get("/digg/user")
                .then()
                .statusCode(200)
                .body("$.size()", is(5))
                .body("[0].name", equalTo("Kund 1"))
                .body("[4].name", equalTo("Kund 5"));

        // 3. Hämta andra sidan med GET (page=1, size=5)
        given()
                .queryParam("page", 1)
                .queryParam("size", 5)
                .when()
                .get("/digg/user")
                .then()
                .statusCode(200)
                .body("$.size()", is(5))
                .body("[0].name", equalTo("Kund 6"))
                .body("[4].name", equalTo("Kund 10"));

        // 4. Hämta sida utanför intervallet (page=2, size=5)
        given()
                .queryParam("page", 2)
                .queryParam("size", 5)
                .when()
                .get("/digg/user")
                .then()
                .statusCode(200)
                .body("$.size()", is(0));
    }

    @Test
    void add_should_create_customer() {
        String json = """
            {
              "name": "Urho Kekkonen",
              "address": "Mariegatan 2, 00170 Helsingfors, Finland",
              "email": "presidentti@tpk.fi",
              "telephone": "+358 29 5226000"
            }
            """;

        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("/digg/user")
                .then()
                .statusCode(201)
                .body("name", equalTo("Urho Kekkonen"))
                .body("email", equalTo("presidentti@tpk.fi"));
    }
}