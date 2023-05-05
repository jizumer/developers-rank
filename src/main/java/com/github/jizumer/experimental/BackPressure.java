package com.github.jizumer.experimental;

import io.smallrye.mutiny.Multi;

import java.time.Duration;

public class BackPressure {

    public static void main(String[] args) {
        System.out.println("Back Pressure exercise");

        Multi.createFrom().ticks().every(Duration.ofSeconds(2))
                .subscribe()
                .with(System.out::println);
    }
}
