package ru.itmo.commands;


import ru.itmo.commands.request.CommandRequest;
import ru.itmo.commands.request.RequestType;
import ru.itmo.managers.commandManager.CommandManager;

import java.util.Map;

public class HelpCommand extends Command{

    public HelpCommand() {
        setTargetTitleForUserInput("help");
        setDescription("help : вывести справку по доступным командам");
    }

    @Override
    public CommandRequest execute(CommandManager commandManager, String arguments) {
        for (Map.Entry<String, Command> command : commandManager.getCommands().entrySet()) {
            System.out.println(command.getValue().getDescription());
        }

        CommandRequest commandRequest = new CommandRequest();
        commandRequest.setRequestType(RequestType.INTERNAL);
        return commandRequest;
    }
}
