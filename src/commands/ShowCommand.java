package commands;

import commands.request.RequestType;
import managers.commandManager.CommandManager;
import commands.request.CommandRequest;

public class ShowCommand extends Command{

    public ShowCommand() {
        setTitle("show");
        setDescription("вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
    }

    @Override
    public CommandRequest execute(CommandManager commandManager, String arguments) {
        CommandRequest commandRequest = new CommandRequest();
        commandRequest.setRequestType(RequestType.GET);
        return commandRequest;
    }
}