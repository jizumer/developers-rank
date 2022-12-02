package com.github.jizumer.developer;

import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/developers")
public class DeveloperResource {

    @Inject
    DeveloperService developerService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getDeveloper() {
        return "Basic developer info";
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Developer> getDeveloperByUsername(String username) {
        return developerService.getDeveloperByUsername(username);
    }
}