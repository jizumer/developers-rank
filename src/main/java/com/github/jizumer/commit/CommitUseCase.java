package com.github.jizumer.commit;

import com.github.jizumer.deploy.DeployService;
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

    public Uni<Void> commit(Commit commit) {
        return commitService.save(commit)
                .onItem()
                .call(deployService::deploy);
    }

    public Uni<List<Commit>> findAllCommitsMadeByDeveloper(String username) {
        return commitService.findAllCommitsMadeByDeveloper(username);
    }
}
