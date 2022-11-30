package com.github.jizumer;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Consumes("application/json")
@Path("/commits")
public class CommitResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response postCommit(Commit commit) {
        return Response.status(Status.CREATED).build();
    }
}