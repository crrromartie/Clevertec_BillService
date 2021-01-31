package ru.clevertec.bill.controller.command;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandProvider {
    static Logger logger = LogManager.getLogger();

    private CommandProvider() {
    }

    public static Command takeCommand(String commandName) {
        Command currentCommand = CommandType.NO_SUCH_COMMAND.getCommand();
        if (commandName != null && !commandName.isBlank()) {
            try {
                currentCommand = CommandType.valueOf(commandName.toUpperCase()).getCommand();
            } catch (IllegalArgumentException e) {
                logger.log(Level.ERROR, "Command is not defined " + commandName);
            }
        }
        return currentCommand;
    }
}
