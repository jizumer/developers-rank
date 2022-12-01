package com.github.jizumer.commit;

import io.smallrye.mutiny.Uni;
import lombok.Setter;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CommitService {

    @ConfigProperty(name = "lag")
    @Setter
    Integer lag;

    private final List<Commit> commitStore = new ArrayList<>();

    public List<Commit> findAllCommits() {
        return commitStore.stream().toList();
    }

    public void save(Commit commit) {
        System.out.println("Saving commit " + commit.toString());
        commitStore.add(commit);
    }

    public Uni<List<Commit>> findAllCommitsMadeByDeveloper(String username) {


        return Uni.createFrom().item(commitStore.stream().filter(commit -> commit.getUsername().equals(username)).toList())
                .onItem()
                .delayIt()
                .by(Duration.ofMillis(lag));
    }

    public void clearCommits() {
        commitStore.clear();
    }


}
