package com.github.jizumer;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class DeveloperResourceTest {

    @Test
    void testHelloEndpoint() {
        given()
          .when().get("/developers")
          .then()
             .statusCode(200)
             .body(is("Basic developer info"));
    }

    @Test
    void shouldGetDeveloperInfoById() {
        given()
            .when().get("/developers/1")
            .then()
            .statusCode(200)
            .body("id", is(1));
    }

    @Test
    void shouldGetDeveloperInfoWithId1() {
        given()
            .when().get("/developers/1")
            .then()
            .statusCode(200)
            .body(
                "id", is(1),
                "name", is("John Doe"),
                "username", is("john.doe"),
                "email", is("john.doe@test.com")
            );
    }

    @Test
    void shouldGetDeveloperInfoWithId2() {
        given()
            .when().get("/developers/2")
            .then()
            .statusCode(200)
            .body(
                "id", is(2),
                "name", is("Charles Barrow"),
                "username", is("charles.barrow"),
                "email", is("charles.barrow@test.com")
            );
    }
}