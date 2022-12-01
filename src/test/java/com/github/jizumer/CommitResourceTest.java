package com.github.jizumer;

import com.github.jizumer.commit.Commit;
import com.github.jizumer.commit.CommitService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class CommitResourceTest {

    @InjectSpy
    CommitService commitService;

    @BeforeEach
    void setUp() {
        commitService.clearCommits();
    }

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

    @Test
    void shouldGetAllCommitsMadeByADeveloper() {
        final var firstCommit = Commit.builder()
            .timestamp(LocalDateTime.parse("2022-11-29T10:24:22.876813"))
            .hash("5f1bce8")
            .username("john.doe")
            .repository("developers-rank")
            .branch("main")
            .comment("This is my first commit")
            .lines(2)
            .build();

        final var secondCommit = Commit.builder()
            .timestamp(LocalDateTime.parse("2022-11-29T16:44:22.876813"))
            .hash("1wetr42")
            .username("john.doe")
            .repository("developers-rank")
            .branch("main")
            .comment("This is my second commit")
            .lines(10)
            .build();

        final var commitFromAnotherDeveloper = Commit.builder()
            .timestamp(LocalDateTime.parse("2022-11-23T16:44:22.876813"))
            .hash("qws2341")
            .username("charles.barrow")
            .repository("developers-rank")
            .branch("main")
            .comment("This is one commit from Charles")
            .lines(21)
            .build();

        commitService.save(firstCommit);
        commitService.save(secondCommit);
        commitService.save(commitFromAnotherDeveloper);

        given()
            .when().get("/commits/developers/john.doe")
            .then()
            .statusCode(200)
            .body("$.size()", is(2));
    }
}