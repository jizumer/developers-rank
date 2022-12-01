package com.github.jizumer;

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
    public Uni<List<Commit>> getAllCommitsByDeveloper(String username) {
        return commitService.findAllCommitsMadeByDeveloper(username);
    }
}