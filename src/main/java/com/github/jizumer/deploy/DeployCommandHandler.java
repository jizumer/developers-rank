package com.github.jizumer.deploy;

import com.github.jizumer.commit.CommitCommand;
import com.github.jizumer.shared.CommandHandler;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DeployCommandHandler implements CommandHandler<DeployCommand> {

    @Inject
    DeployService deployService;

    @Override
    public Class subscribedTo() {
        return CommitCommand.class;
    }

    @Override
    public Uni<Void> consume(DeployCommand command) {
        return deployService.deploy();
    }
}
