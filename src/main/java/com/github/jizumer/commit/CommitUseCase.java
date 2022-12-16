package com.github.jizumer.commit;

import com.github.jizumer.deploy.DeployService;
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
    DeployService deployService;

    @Inject
    SyncCommandBus syncCommandBus;

    public Uni<Void> commit(Commit commit) {
        return Uni.createFrom().voidItem().onItem()
            .invoke(() -> syncCommandBus.dispatch(new CommitCommand(commit)))
            .call(deployService::deploy);
    }

    public Uni<List<Commit>> findAllCommitsMadeByDeveloper(String username) {
        return commitService.findAllCommitsMadeByDeveloper(username);
    }
}
