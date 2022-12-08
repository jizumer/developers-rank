package com.github.jizumer.shared;


public interface CommandHandler<CommandType extends Command> {
    Class<CommandType> subscribedTo();

    void consume(CommandType event);
}
