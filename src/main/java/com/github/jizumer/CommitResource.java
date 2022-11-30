package com.github.jizumer;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Consumes("application/json")
@Path("/commits")
public class CommitResource {

    @Inject
    CommitService commitService;

    @POST
    public void postCommit(Commit commit) {

    }
}