package com.github.jizumer;

import com.github.jizumer.commit.Commit;
import com.github.jizumer.commit.CommitService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

@QuarkusTest
class DeveloperResourceTest {

    @InjectSpy
    CommitService commitService;

    @BeforeEach
    void setUp() {
        commitService.clearCommits();
    }

    @Test
    void shouldGetDeveloperInfoByCharlesBarrow() {
        given()
                .when().get("/developers/charles.barrow")
                .then()
                .statusCode(200)
                .body("username", is("charles.barrow"));
    }

    @Test
    void shouldGetDeveloperInfoWithJohnDoe() {
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
    void shouldGetDeveloperInfoWithCharlesBarrow() {
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
    void shouldGetDeveloperInfoWithJohnSummer() {
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

    @Test
    void shouldIncludeNumberCommitsMadeWhenGettingDeveloperInfo() {
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
                .when().get("/developers/john.doe")
                .then()
                .statusCode(200)
                .body("numberOfCommits", is(2));
    }

    @Test
    void shouldReturnErrorWhenCommitServiceAlsoReturnsErrorWhenGettingDeveloperInfo() {
        when(commitService.findAllCommitsMadeByDeveloper("john.doe"))
                .thenThrow(NullPointerException.class);

        given()
                .when().get("/developers/john.doe")
                .then()
                .statusCode(500);
    }
}