package com.github.jizumer.experimental.backpressure;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.subscription.BackPressureStrategy;
import io.smallrye.mutiny.subscription.MultiSubscriber;
import org.reactivestreams.Subscription;

public class _09_BackPressureDropping {

    public static void main(String[] args) {
        System.out.println("⚡️ Back-pressure: drop");

        Multi.createFrom().emitter(emitter -> ConfigurableEmitter.emitEvery(emitter, 250), BackPressureStrategy.ERROR)
                //.onOverflow().invoke(s -> System.out.print("🚨 ")).drop() // Comment out for some fun
                .subscribe().withSubscriber(new MultiSubscriber<Object>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(5);
                    }

                    @Override
                    public void onItem(Object s) {
                        System.out.print(s + " ");
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
