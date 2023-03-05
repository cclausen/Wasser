package de.horroreyes.wasser.it;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import de.horroreyes.wasser.model.Achievement;
import de.horroreyes.wasser.model.Person;
import de.horroreyes.wasser.model.enums.Status;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AchievementControllerIntegrationTest {
    @LocalServerPort
    private int port;
    private static Person person;

    @BeforeAll
    public static void setupAll() {
        person = new Person("Frodo", "Baggins", Status.ACTIVE);
        person.setId(1L);
    }

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost/api/achievements";
        RestAssured.port = port;
    }

    @Test
    void testIndexAchievements() {
        get("/all").then().assertThat()
                .statusCode(200)
                .body("$", hasSize(2));
    }

    @Test
    void testCreate() {
        with().contentType("application/json")
                .body(new Achievement("Eisbaden", LocalDateTime.now(), person))
                .post("/").then().assertThat()
                .statusCode(200);

    }

    @Test
    void testGetAchievement() {
        with().contentType("application/json")
                .body(new Achievement("Eisbaden", LocalDateTime.now(), person))
                .post("/").then().assertThat()
                .statusCode(200);

        get("/3").then().assertThat()
                .statusCode(200)
                .body("name", equalTo("Eisbaden"));

        delete("/3").then().assertThat()
                .statusCode(200);
    }

    @Test
    void testDeleteAchievement() {
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
        Person anotherPerson = new Person("Bilbo", "Baggins", Status.ACTIVE);
        anotherPerson.setId(2L);
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