package com.github.jizumer.experimental.backpressure;

import io.smallrye.mutiny.subscription.MultiEmitter;

public class ConfigurableEmitter {

    public static void emitEvery(MultiEmitter<? super Object> emitter, long emissionPeriod) {
        new Thread(() -> {
            long n = 0;
            while (true) {
                emitter.emit("📦 " + ++n);
                try {
                    Thread.sleep(emissionPeriod);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
