package com.github.jizumer.commit;

import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.config.inject.ConfigProperty;

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

    @ConfigProperty(name = "simulate-commits", defaultValue = "false")
    boolean simulateCommits;

    private final List<String> developers = List.of(
            "john.doe",
            "charles.barrow",
            "john.summer");


    @Inject
    CommitService commitService;

    void onStart(@Observes StartupEvent startupEvent) {
        if (simulateCommits &&
                ProfileManager.getActiveProfile().equals("dev")) {
            Multi.createFrom().ticks().every(Duration.ofSeconds(2))
                    .subscribe()
                    .with(this::generateRandomCommit);
        }
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
                .build()).await().atMost(Duration.ofSeconds(30));
    }


}
