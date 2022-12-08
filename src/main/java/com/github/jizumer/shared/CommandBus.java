package com.github.jizumer.shared;

public interface CommandBus {
    void dispatch(final Command command);
}
