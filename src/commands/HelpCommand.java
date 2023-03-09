package commands;

import commands.request.CommandRequest;
import commands.request.RequestType;
import managers.commandManager.CommandManager;

import java.util.Map;
import java.util.Scanner;

public class HelpCommand extends Command{

    public HelpCommand() {
        setTitle("help");
        setDescription("вывести справку по доступным командам");
    }

    @Override
    public CommandRequest execute(CommandManager commandManager, String arguments) {
        for (Map.Entry<String, Command> command : commandManager.getCommands().entrySet()) {
            System.out.printf("%s : %s", command.getValue().getTitle(), command.getValue().getDescription());
            System.out.println();
        }

        CommandRequest commandRequest = new CommandRequest();
        commandRequest.setRequestType(RequestType.INTERNAL);
        return commandRequest;
    }
}