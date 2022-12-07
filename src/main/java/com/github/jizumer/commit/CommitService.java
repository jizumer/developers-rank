package com.github.jizumer.commit;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CommitService {

    @ConfigProperty(name = "lag")
    Integer lag;

    private final List<Commit> commitStore = new ArrayList<>();

    @Inject
    @Channel("commit-events")
    Emitter<Commit> commitEventEmitter;

    public List<Commit> findAllCommits() {
        return commitStore.stream().toList();
    }

    public Uni<Void> save(Commit commit) {
        return Uni
                .createFrom()
                .item(commit)
                .log("Saving commit " + commit.toString())
                .invoke(commitStore::add)
                .invoke(commitEventEmitter::send)
                .replaceWithVoid();
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

    @Incoming("config-events")
    public void consumeConfigEvent(Integer lag) {
        System.out.println("Configuring new lag: " + lag);
        this.lag = lag;
    }


}
