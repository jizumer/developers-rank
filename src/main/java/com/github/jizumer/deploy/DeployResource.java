package com.github.jizumer.deploy;

import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("deploy")
public class DeployResource {
    @Inject
    DeployService deployService;

    @POST
    public Uni<Void> triggerDeployment() {
        return deployService.deploy();

    }
}
