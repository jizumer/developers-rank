package com.github.jizumer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/developers")
public class DeveloperResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getDeveloper() {
        return "Basic developer info";
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getDeveloper(int id) {
        return "{ \"id\": 1 }";
    }
}