package com.github.jizumer.shared;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SyncCommandBus implements CommandBus {


    //Assuming for now that we will only support one handler per command
    Map<Class, CommandHandler> subscribedCommandHandlers = new HashMap<>();


    public SyncCommandBus(final Set<CommandHandler> commandHandlers) {
        commandHandlers.forEach(this::subscribe);
    }

    private void subscribe(final CommandHandler commandHandler) {
        subscribedCommandHandlers.put(
                commandHandler.subscribedTo(), commandHandler);
    }

    @Override
    public void dispatch(final Command command) {
        subscribedCommandHandlers.entrySet().stream()
                .filter(handler -> command.getClass().equals(handler.getValue().subscribedTo()))
                .findFirst()
                .ifPresent(handler -> handler.getValue().consume(command));

    }
}
