package com.github.jizumer.shared;

import io.smallrye.mutiny.Uni;

public interface CommandBus {
    Uni<Void> dispatch(final Command command);
}
