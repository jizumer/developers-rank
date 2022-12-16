package com.github.jizumer.commit;

import com.github.jizumer.deploy.DeployCommand;
import com.github.jizumer.shared.AsyncCommandBus;
import com.github.jizumer.shared.SyncCommandBus;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class CommitUseCase {

    @Inject
    CommitService commitService;

    @Inject
    SyncCommandBus syncCommandBus;

    @Inject
    AsyncCommandBus asyncCommandBus;

    public Uni<Void> commit(Commit commit) {
        return Uni.createFrom().voidItem().onItem()
                .call(() ->
                        syncCommandBus.dispatch(new CommitCommand(commit)))
                .call(() ->
                        asyncCommandBus.dispatch(new DeployCommand()));
    }

    public Uni<List<Commit>> findAllCommitsMadeByDeveloper(String username) {
        return commitService.findAllCommitsMadeByDeveloper(username);
    }
}
