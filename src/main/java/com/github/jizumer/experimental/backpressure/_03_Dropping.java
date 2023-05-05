package com.github.jizumer.experimental.backpressure;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.infrastructure.Infrastructure;

import java.time.Duration;

public class _03_Dropping {

    public static void main (String[] args) {
        Multi.createFrom().ticks().every(Duration.ofMillis(10))
                .onOverflow().drop()
                .invoke(x -> System.out.println("Dropping item " + x))
                .emitOn(Infrastructure.getDefaultExecutor())
                .onItem().transform(_03_Dropping::canOnlyConsumeOneItemPerSecond)
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
