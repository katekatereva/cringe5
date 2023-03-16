package commands;

import commands.request.CommandRequest;
import commands.request.RequestType;
import managers.commandManager.CommandManager;

public class ClearCommand extends Command{

    public ClearCommand() {
        setTarget("clear");
        setDescription("clear : очистить коллекцию");
    }

    @Override
    public CommandRequest execute(CommandManager commandManager, String arguments) {
        CommandRequest commandRequest = new CommandRequest();
        commandRequest.setRequestType(RequestType.DELETE);
        return commandRequest;
    }
}
