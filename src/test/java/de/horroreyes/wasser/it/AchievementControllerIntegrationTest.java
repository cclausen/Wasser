package de.horroreyes.wasser.it;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import de.horroreyes.wasser.model.Achievement;
import de.horroreyes.wasser.model.Person;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AchievementControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost/api/achievements";
        RestAssured.port = port;
    }

    @Test
    void testIndexAchievements() {
        Person person = RestAssured.get("http://localhost/api/persons/1").as(Person.class);
        with().contentType("application/json")
                .body(new Achievement("Eisbaden", LocalDateTime.now(), person))
                .post("/").then().assertThat()
                .statusCode(200);

        with().contentType("application/json")
                .body(new Achievement("Eisbaden2", LocalDateTime.now(), person))
                .post("/").then().assertThat()
                .statusCode(200);

        get("/all").then().assertThat()
                .statusCode(200)
                .body("$", hasSize(2));
    }

    @Test
    void testCreate() {
        Person person = RestAssured.get("http://localhost/api/persons/1").as(Person.class);
        with().contentType("application/json")
                .body(new Achievement("Eisbaden", LocalDateTime.now(), person))
                .post("/").then().assertThat()
                .statusCode(200);

    }

    @Test
    void testGetAchievement() {
        Person person = RestAssured.get("http://localhost/api/persons/1").as(Person.class);
        with().contentType("application/json")
                .body(new Achievement("Eisbaden", LocalDateTime.now(), person))
                .post("/").then().assertThat()
                .statusCode(200);

        get("/1").then().assertThat()
                .statusCode(200)
                .body("name", equalTo("Eisbaden"));

        delete("/1").then().assertThat()
                .statusCode(200);
    }

    @Test
    void testDeleteAchievement() {
        Person person = RestAssured.get("http://localhost/api/persons/1").as(Person.class);
        with().contentType("application/json")
                .body(new Achievement("Eisbaden", LocalDateTime.now(), person))
                .post("/").then().assertThat()
                .statusCode(200);
        delete("/2").then().assertThat()
                .statusCode(200);
        get("/2").then().assertThat()
                .statusCode(404);

    }

    @Test
    void testGetAchievementsByPerson() {
        Person anotherPerson = RestAssured.get("http://localhost/api/persons/2").as(Person.class);
        get("/byPerson/" + anotherPerson.getId()).then().assertThat()
                .statusCode(200)
                .body("$", hasSize(0));

        with().contentType("application/json")
                .body(new Achievement("Eisbaden", LocalDateTime.now(), anotherPerson))
                .post("/").then().assertThat()
                .statusCode(200);

        get("/byPerson/" + anotherPerson.getId()).then().assertThat()
                .statusCode(200)
                .body("$", hasSize(1));
    }
}