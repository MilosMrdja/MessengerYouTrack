package com.messanger.api.command;

public interface Command {
    String name();
    String description();
    void execute(String[] args);
}
