package com.github.jizumer.experimental.backpressure;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.subscription.BackPressureStrategy;
import io.smallrye.mutiny.subscription.MultiSubscriber;
import org.reactivestreams.Subscription;

public class _10_BackPressureBuffering {

    public static void main(String[] args) {
        System.out.println("⚡️ Back-pressure: buffer");

        Multi.createFrom().emitter(emitter -> ConfigurableEmitter.emitEvery(emitter, 250), BackPressureStrategy.ERROR)
                .onOverflow()
                //.invoke((object) -> System.out.println("Overflow in buffer with element " + object))
                .buffer(32)
                .subscribe().withSubscriber(new MultiSubscriber<Object>() {
                    private Subscription subscription;
                    @Override
                    public void onSubscribe(Subscription s) {
                        this.subscription = s;
                        s.request(1);
                    }

                    @Override
                    public void onItem(Object s) {
                        System.out.println(s);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        this.subscription.request(5);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("\n✋ " + throwable.getMessage());
                    }

                    @Override
                    public void onCompletion() {
                        System.out.println("\n✅");
                    }
                });
    }
}
