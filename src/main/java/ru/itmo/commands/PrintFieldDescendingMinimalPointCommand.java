package ru.itmo.commands;

import ru.itmo.commands.request.CommandRequest;
import ru.itmo.commands.request.RequestType;
import ru.itmo.commands.response.CommandResponse;
import ru.itmo.managers.commandManager.CommandManager;

import java.util.ArrayList;

public class PrintFieldDescendingMinimalPointCommand extends Command{

    public PrintFieldDescendingMinimalPointCommand() {
        setTargetTitleForUserInput("print_field_descending_minimal_point");
        setDescription("print_field_descending_minimal_point : вывести значения поля minimalPoint всех элементов в порядке убывания");
    }

    @Override
    public CommandRequest execute(CommandManager commandManager, String arguments) {

        CommandRequest commandRequest = new CommandRequest();
        commandRequest.setRequestType(RequestType.GET_MINIMAL_POINT);

        CommandResponse commandResponse = commandManager.getResponse(commandRequest);

        ArrayList<Integer> minimalPoints = (ArrayList<Integer>) commandResponse.getObject();

        for (int i = 0; i < minimalPoints.size(); i++) {
            System.out.printf("%d. %d", i + 1, minimalPoints.get(i));
            System.out.println();
        }

        commandRequest.setRequestType(RequestType.INTERNAL);
        return commandRequest;

    }
}
