package com.github.jizumer.shared;


import io.smallrye.mutiny.Uni;

public interface AsyncCommandHandler<CommandType extends Command> {

    Uni<Void> consume(CommandType command);
}
