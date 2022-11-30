package com.github.jizumer;

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
        return commitService.findAllCommitsMadeByDeveloper(username);
    }
}