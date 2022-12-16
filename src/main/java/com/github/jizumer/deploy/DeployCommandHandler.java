package com.github.jizumer.deploy;

import com.github.jizumer.shared.AsyncCommandHandler;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DeployCommandHandler implements AsyncCommandHandler<DeployCommand> {

    @Inject
    DeployService deployService;

    @Incoming("async-commands")
    public Uni<Void> consume(DeployCommand deployCommand) {
        return deployService.deploy();
    }
}
