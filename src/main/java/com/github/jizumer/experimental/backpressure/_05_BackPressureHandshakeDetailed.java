package com.github.jizumer.experimental.backpressure;

import io.smallrye.mutiny.Multi;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class _05_BackPressureHandshakeDetailed {
    public static void main(String[] args) {
        Multi.createFrom().range(0, 10)
                .onSubscription().invoke(sub -> System.out.println("Received subscription: " + sub))
                .onRequest().invoke(req -> System.out.println("Got a request: " + req))
                .onItem().transform(i -> i * 100)
                .subscribe().withSubscriber(new Subscriber<Integer>() {

                                                private Subscription subscription;

                                                @Override
                                                public void onSubscribe(Subscription s) {
                                                    this.subscription = s;
                                                    s.request(1);
                                                }

                                                @Override
                                                public void onNext(Integer item) {
                                                    System.out.println("Got item " + item);
                                                    subscription.request(1);
                                                }

                                                @Override
                                                public void onError(Throwable t) {
                                                    // ...
                                                }

                                                @Override
                                                public void onComplete() {
                                                    // ...
                                                }
                                            }
                );
    }
}
