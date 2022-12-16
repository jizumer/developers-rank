package com.github.jizumer.shared;

import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class SyncCommandBus implements CommandBus {

    @Inject
    Instance<CommandHandler<?>> commandHandlers;

    //Assuming for now that we will only support one handler per command
    Map<Class, CommandHandler> subscribedCommandHandlers = new HashMap<>();

    public SyncCommandBus(Instance<CommandHandler<?>> commandHandlers) {
        this.commandHandlers = commandHandlers;
        commandHandlers.forEach(this::subscribe);
    }

    private void subscribe(final CommandHandler commandHandler) {
        subscribedCommandHandlers.put(
                commandHandler.subscribedTo(), commandHandler);
    }

    @Override
    public Uni<Void> dispatch(final Command command) {
        return subscribedCommandHandlers.entrySet().stream()
                .filter(handler -> command.getClass().equals(handler.getValue().subscribedTo()))
                .findFirst().get().getValue()
                .consume(command);

    }
}
