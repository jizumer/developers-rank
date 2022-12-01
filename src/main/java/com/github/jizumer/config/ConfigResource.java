package com.github.jizumer.config;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Consumes("application/json")
@Path("/config")
public class ConfigResource {
    @POST
    public void configureLag(Integer lag) {
        System.out.println("configuring new lag: " + lag);
    }
}
