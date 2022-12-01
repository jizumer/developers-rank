package com.github.jizumer.commit;

import io.quarkus.runtime.StartupEvent;
import io.smallrye.mutiny.Multi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.random.RandomGenerator;

@ApplicationScoped
public class CommitSimulator {


    private final List<String> developers = List.of(
            "john.doe",
            "charles.barrow",
            "john.summer");


    @Inject
    CommitService commitService;

    void onStart(@Observes StartupEvent startupEvent) {
        Multi.createFrom().ticks().every(Duration.ofSeconds(2))
                .subscribe()
                .with(this::generateRandomCommit);
    }

    private void generateRandomCommit(Long executionTime) {

        commitService.save(Commit.builder()
                .timestamp(LocalDateTime.now())
                .hash(UUID.randomUUID().toString())
                .username(
                        developers
                                .get(RandomGenerator.getDefault().nextInt(developers.size())))
                .repository("developer-rank")
                .branch("master")
                .comment("Commit number " + executionTime)
                .lines(RandomGenerator.getDefault().nextInt(100))
                .build());
    }


}
