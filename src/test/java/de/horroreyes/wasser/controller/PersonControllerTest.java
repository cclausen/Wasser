package de.horroreyes.wasser.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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
        get("/").then().assertThat().statusCode(200).body("$.content", hasSize(5)).body("lotto.lottoId", equalTo(5));
    }

    @Test
    void create() {
    }

    @Test
    void getPerson() {
    }

    @Test
    void deletePerson() {
    }
}