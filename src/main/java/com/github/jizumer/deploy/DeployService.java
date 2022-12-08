package com.github.jizumer.deploy;

import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.util.random.RandomGenerator;

@ApplicationScoped
public class DeployService {
    private static final int MAX_DEPLOYMENT_DURATION = 15;
    private static final int MIN_DEPLOYMENT_DURATION = 5;

    public Uni<Void> deploy() {
        return Uni.createFrom()
                .voidItem()
                .log("Deploying!")
                .onItem()
                .delayIt()
                .by(Duration.ofSeconds(
                        RandomGenerator.getDefault().nextInt(MIN_DEPLOYMENT_DURATION, MAX_DEPLOYMENT_DURATION)))
                .log("Deployed!");
    }
}
