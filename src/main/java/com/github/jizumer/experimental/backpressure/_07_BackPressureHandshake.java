package com.github.jizumer.experimental.backpressure;

import io.smallrye.mutiny.Multi;

public class _07_BackPressureHandshake {
    public static void main(String[] args){
        Multi.createFrom().range(0, 100)
                .onSubscription().invoke(sub -> System.out.println("Received subscription: " + sub))
                .onRequest().invoke(req -> System.out.println("Got a request: " + req))
                .filter(i -> i % 2 == 0)
                .onItem().transform(i -> i * 100)
                .subscribe().with(
                        i -> System.out.println("i: " + i)
                );
    }
}
