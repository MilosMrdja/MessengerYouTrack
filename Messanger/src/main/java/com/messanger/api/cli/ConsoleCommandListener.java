package com.messanger.api.cli;

import com.messanger.api.command.Command;
import com.messanger.api.command.CommandRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class ConsoleCommandListener implements CommandLineRunner {

    private final CommandRegistry commandRegistry;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("🟢 YouTrack Console started.");
        commandRegistry.printAvailableCommands();

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("/exit")) {
                System.out.println("👋 Exiting...");
                break;
            }

            if (input.isEmpty()) continue;

            String[] parts = input.split(" ");
            String commandName = parts[0];
            String[] commandArgs = java.util.Arrays.copyOfRange(parts, 1, parts.length);

            Command cmd = commandRegistry.get(commandName);
            if (cmd != null) {
                cmd.execute(commandArgs);
            } else {
                System.out.println("⚠️ Unknown command.");
            }
        }

        scanner.close();
    }
}
