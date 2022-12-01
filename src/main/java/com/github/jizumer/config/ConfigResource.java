package com.github.jizumer.config;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Consumes("application/json")
@Path("/config")
public class ConfigResource {

    @Inject
    @Channel("config-events")
    Emitter<Integer> configEmitter;

    @POST
    public void configureLag(Integer lag) {
        configEmitter.send(lag);
    }
}
