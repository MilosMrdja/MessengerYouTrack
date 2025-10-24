package com.messanger.api.command;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommandRegistry {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandRegistry(List<Command> commandList) {
        for (Command c : commandList) {
            commands.put(c.name(), c);
        }
    }

    public Command get(String name) {
        return commands.get(name);
    }

    public void printAvailableCommands() {
        System.out.println("Available commands:");
        commands.values().forEach(c -> System.out.println("  " + c.description()));
        System.out.println("  /exit");
    }
}
