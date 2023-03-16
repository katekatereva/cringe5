package commands;

import commands.interactives.update.UpdateInteractiveLabWork;
import commands.request.CommandRequest;
import commands.request.RequestType;
import commands.response.CommandResponse;
import managers.commandManager.CommandManager;
import models.LabWork;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UpdateCommand extends Command{

    private Scanner scanner;

    public UpdateCommand(Scanner scanner) {

        setTarget("update");
        setDescription("update id {element} : обновить значение элемента коллекции, id которого равен заданному");

        this.scanner = scanner;
    }

    @Override
    public CommandRequest execute(CommandManager commandManager, String arguments) {
        Map<String, Object> parameters = new HashMap<>();
        CommandRequest commandRequest = new CommandRequest();

        try {
            int id = Integer.parseInt(arguments);
            parameters.put("id", id);
            commandRequest.setRequestType(RequestType.GET);
            commandRequest.setParameters(parameters);

            CommandResponse commandResponse = commandManager.getResponse(commandRequest);
            Class typeOfObjectInResponse = commandResponse.getTypeOfObject();
            if (typeOfObjectInResponse == ArrayDeque.class) {
                ArrayDeque<LabWork> labWorks = (ArrayDeque<LabWork>) commandResponse.getObject();
                if (labWorks.size() > 0) {

                    LabWork labWork = new LabWork(labWorks.getFirst());
                    UpdateInteractiveLabWork.updateLabWork(labWork, scanner);
                    commandRequest.setObject(labWork);
                    commandRequest.setRequestType(RequestType.UPDATE);

                } else {
                    System.out.println("Лабораторная работа с таким ID не найдена");
                }
            }
            return commandRequest;

        } catch (NumberFormatException e) {
            System.out.println("Вы должны ввести число");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        commandRequest.setRequestType(RequestType.INTERNAL);
        return commandRequest;
    }
}