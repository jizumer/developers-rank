package com.github.jizumer.experimental.backpressure;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.subscription.BackPressureStrategy;
import io.smallrye.mutiny.subscription.MultiSubscriber;
import org.reactivestreams.Subscription;

import java.util.function.Consumer;

public class _04_Buffering {
    public static void main(String[] args) {
        System.out.println("⚡️ Back-pressure: buffering visualised");

        Multi.createFrom().emitter(emitter -> ConfigurableEmitter.emitEvery(emitter, 250), BackPressureStrategy.ERROR)
                .onItem().invoke((Consumer<Object>) System.out::println)
                .onOverflow().buffer(64)
                .subscribe().withSubscriber(new MultiSubscriber<Object>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(5);
                        periodicallyRequest(s);
                    }

                    private void periodicallyRequest(Subscription s) {
                        new Thread(() -> {
                            while (true) {
                                try {
                                    Thread.sleep(5000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                System.out.println("           🤷 request");
                                s.request(2);
                            }
                        }).start();
                    }

                    @Override
                    public void onItem(Object s) {
                        System.out.println("           ➡️ " + s);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("✋ " + throwable.getMessage());
                    }

                    @Override
                    public void onCompletion() {
                        System.out.println("✅");
                    }
                });
    }
}
