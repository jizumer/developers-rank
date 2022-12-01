package com.github.jizumer;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/commits")
@RegisterRestClient(configKey = "commit")
public interface CommitGateway {

    @GET
    @Path("/developers/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    List<Commit> getAllCommitsMadeByDeveloper(@PathParam("username") String username);
}