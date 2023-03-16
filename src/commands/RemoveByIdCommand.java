package commands;

import commands.request.CommandRequest;
import commands.request.RequestType;
import managers.commandManager.CommandManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RemoveByIdCommand extends Command {

    private Scanner scanner;

    public RemoveByIdCommand(Scanner scanner) {
        setTarget("remove_by_id");
        setDescription("remove_by_id id : удалить элемент из коллекции по его id");
        this.scanner = scanner;
    }

    @Override
    public CommandRequest execute(CommandManager commandManager, String arguments) {

        Map<String, Object> parameters = new HashMap<>();

        try {
            int id = Integer.parseInt(arguments);
            parameters.put("id", id);
            CommandRequest commandRequest = new CommandRequest();
            commandRequest.setRequestType(RequestType.DELETE);
            commandRequest.setParameters(parameters);
            return commandRequest;

        } catch (NumberFormatException e) {
            System.out.println("Вы должны ввести число");
        }

        return null;
    }
}