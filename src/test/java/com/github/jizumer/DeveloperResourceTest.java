package com.github.jizumer;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class DeveloperResourceTest {

    @Test
    void shouldGetDeveloperInfoById() {
        given()
                .when().get("/developers/charles.barrow")
                .then()
                .statusCode(200)
                .body("username", is("charles.barrow"));
    }

    @Test
    void shouldGetDeveloperInfoWithId1() {
        given()
                .when().get("/developers/john.doe")
                .then()
                .statusCode(200)
                .body(
                        "name", is("John Doe"),
                        "username", is("john.doe"),
                        "email", is("john.doe@test.com")
                );
    }

    @Test
    void shouldGetDeveloperInfoWithId2() {
        given()
                .when().get("/developers/charles.barrow")
                .then()
                .statusCode(200)
                .body(
                        "name", is("Charles Barrow"),
                        "username", is("charles.barrow"),
                        "email", is("charles.barrow@test.com")
                );
    }

    @Test
    void shouldGetDeveloperInfoWithId3() {
        given()
                .when().get("/developers/john.summer")
                .then()
                .statusCode(200)
                .body(
                        "name", is("John Summer"),
                        "username", is("john.summer"),
                        "email", is("john.summer@test.com")
                );
    }
}