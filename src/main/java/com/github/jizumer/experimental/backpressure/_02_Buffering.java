package com.github.jizumer.experimental.backpressure;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.infrastructure.Infrastructure;

import java.time.Duration;

public class _02_Buffering {
    public static void main(String[] args){
        Multi.createFrom().ticks().every(Duration.ofMillis(10))
                .onOverflow().buffer(250)
                .emitOn(Infrastructure.getDefaultExecutor())
                .onItem().transform(_02_Buffering::canOnlyConsumeOneItemPerSecond)
                .subscribe().with(
                        item -> System.out.println("Got item: " + item),
                        failure -> System.out.println("Got failure: " + failure)
                );
    }

    private static Long canOnlyConsumeOneItemPerSecond(Long aLong) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return aLong;
    }
}
