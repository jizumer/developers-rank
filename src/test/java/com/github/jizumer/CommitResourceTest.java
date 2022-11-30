package com.github.jizumer;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

@QuarkusTest
class CommitResourceTest {

    @Test
    void shouldPostACommit() {
        given()
            .contentType(ContentType.JSON)
            .body(
                """
                  {
                    "timestamp": "2022-11-29T16:44:22.876813",
                    "hash": "5f1bce8",
                    "username": "john.doe",
                    "repository": "developers-rank",
                    "branch": "main",
                    "comment": "This is a commit",
                    "lines": 2
                  }
                    """)
            .when().post("/commits")
            .then()
            .statusCode(201);
    }
}