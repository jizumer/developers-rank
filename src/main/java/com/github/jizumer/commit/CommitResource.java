package com.github.jizumer.commit;

import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;

@Consumes("application/json")
@Path("/commits")
public class CommitResource {

    @Inject
    CommitUseCase commitUseCase;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> postCommit(Commit commit) {
        return commitUseCase.commit(commit)
                .onItem()
                .transform(
                        unused -> Response.status(Status.CREATED).build());
    }

    @GET
    @Path("/developers/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<List<Commit>> getAllCommitsByDeveloper(String username) {
        return commitUseCase.findAllCommitsMadeByDeveloper(username);
    }
}