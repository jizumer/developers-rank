package com.github.jizumer.commit;

import com.github.jizumer.shared.CommandHandler;
import javax.inject.Inject;

public class CommitCommandHandler implements CommandHandler<CommitCommand> {

    @Inject
    CommitService commitService;

    @Override
    public Class subscribedTo() {
        return CommitCommand.class;
    }

    @Override
    public void consume(CommitCommand command) {
        commitService.save(command.getCommit());
    }
}
