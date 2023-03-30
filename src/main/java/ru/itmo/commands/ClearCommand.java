package ru.itmo.commands;


import ru.itmo.commands.request.CommandRequest;
import ru.itmo.commands.request.RequestType;
import ru.itmo.managers.commandManager.CommandManager;

public class ClearCommand extends Command{

    public ClearCommand() {
        setTargetTitleForUserInput("clear");
        setDescription("clear : очистить коллекцию");
    }

    @Override
    public CommandRequest execute(CommandManager commandManager, String arguments) {
        CommandRequest commandRequest = new CommandRequest();
        commandRequest.setRequestType(RequestType.DELETE);
        return commandRequest;
    }
}
