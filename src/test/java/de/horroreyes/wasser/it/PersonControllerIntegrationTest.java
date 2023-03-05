package de.horroreyes.wasser.it;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import de.horroreyes.wasser.model.Person;
import de.horroreyes.wasser.model.enums.Status;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PersonControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost/api/persons";
        RestAssured.port = port;
    }

    @Test
    void indexPersons() {
        get("/").then().assertThat()
                .statusCode(200)
                .body("$", hasSize(2));
    }

    @Test
    void create() {
        with().contentType("application/json")
                .body(new Person("Frodo", "Baggins", Status.ACTIVE))
                .post("/").then().assertThat()
                .statusCode(200);

        delete("/3").then().assertThat()
                .statusCode(200);
    }

    @Test
    void getPerson() {
        get("/1").then().assertThat()
                .statusCode(200)
                .body("lastname", equalTo("Baggins"));
    }

    @Test
    void deletePerson() {
        delete("/2").then().assertThat()
                .statusCode(200);
        get("/2").then().assertThat()
                .statusCode(404);

        // Assure valid state for other tests
        with().contentType("application/json")
                .body(new Person("Frodo", "Baggins", Status.ACTIVE))
                .post("/").then().assertThat()
                .statusCode(200);
    }
}