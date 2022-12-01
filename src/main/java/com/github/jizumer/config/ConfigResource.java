package com.github.jizumer.config;

import com.github.jizumer.commit.CommitService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Consumes("application/json")
@Path("/config")
public class ConfigResource {

    @Inject
    CommitService commitService;

    @POST
    public void configureLag(Integer lag) {
        System.out.println("configuring new lag: " + lag);
        commitService.setLag(lag);
    }
}
