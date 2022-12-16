package com.github.jizumer.shared;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AsyncCommandBus implements CommandBus {

    @Inject
    @Channel("async-commands")
    Emitter<Command> asyncCommandEmitter;


    @Override
    public Uni<Void> dispatch(final Command command) {
        return Uni
                .createFrom()
                .voidItem()
                .onItem()
                .invoke(() -> asyncCommandEmitter
                        .send(command));
    }
}
