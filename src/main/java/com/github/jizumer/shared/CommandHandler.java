package com.github.jizumer.shared;


import io.smallrye.mutiny.Uni;

public interface CommandHandler<CommandType extends Command> {
    Class<CommandType> subscribedTo();

    Uni<Void> consume(CommandType command);
}
