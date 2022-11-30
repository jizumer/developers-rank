package com.github.jizumer;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import io.restassured.http.ContentType;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

@QuarkusTest
class CommitResourceTest {

    @InjectSpy
    CommitService commitService;

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

    @Test
    void shouldSaveCommitWhenPostingIt() {
        final var expectedSavedCommit = Commit.builder()
            .timestamp(LocalDateTime.parse("2022-11-29T16:44:22.876813"))
            .hash("5f1bce8")
            .username("john.doe")
            .repository("developers-rank")
            .branch("main")
            .comment("This is a commit")
            .lines(2)
            .build();

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

        assertThat(commitService.findAllCommits()).contains(expectedSavedCommit);
    }
}