package com.github.jizumer;

import java.time.LocalDateTime;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Consumes("application/json")
@Path("/commits")
public class CommitResource {

    @Inject
    CommitService commitService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response postCommit(Commit commit) {

        commitService.save(commit);

        return Response.status(Status.CREATED).build();
    }

    @GET
    @Path("/developers/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Commit> getAllCommitsByDeveloper(String username) {
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
            .lines(2)
            .build();

        return List.of(firstCommit, secondCommit);
    }
}