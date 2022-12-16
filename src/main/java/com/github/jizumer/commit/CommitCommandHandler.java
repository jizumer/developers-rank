package com.github.jizumer.commit;

import com.github.jizumer.shared.CommandHandler;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CommitCommandHandler implements CommandHandler<CommitCommand> {

    @Inject
    CommitService commitService;

    @Override
    public Class subscribedTo() {
        return CommitCommand.class;
    }

    @Override
    public Uni<Void> consume(CommitCommand command) {
        return commitService.save(command.getCommit());
    }
}
