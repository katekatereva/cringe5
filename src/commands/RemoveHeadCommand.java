package commands;

import client.Pam;
import commands.interactives.show.ShowInteractiveLabWork;
import commands.request.CommandRequest;
import commands.request.RequestType;
import commands.response.CommandResponse;
import commands.response.ResponseType;
import managers.commandManager.CommandManager;
import models.LabWork;

import java.util.HashMap;
import java.util.Map;

public class RemoveHeadCommand extends Command{

    public RemoveHeadCommand() {
        setTarget("remove_head");
        setDescription("remove_head: вывести первый элемент коллекции и удалить его");
    }

    @Override
    public CommandRequest execute(CommandManager commandManager, String arguments) {
        CommandRequest commandRequest = new CommandRequest();
        commandRequest.setRequestType(RequestType.GET_FIRST);
        try {
            CommandResponse commandResponse = commandManager.getResponse(commandRequest);

            if (commandResponse.getResponseType() == ResponseType.NOT_FOUND) {
                System.out.println("В списке нет лабораторных работ");
                commandRequest.setRequestType(RequestType.INTERNAL);
            }

            else {

                LabWork first = (LabWork) commandResponse.getObject();

                System.out.println("---------------------------------------------------------------------------");
                ShowInteractiveLabWork.showLabWork(first, 0);
                System.out.println("---------------------------------------------------------------------------");

                commandRequest.setRequestType(RequestType.DELETE);
                Map<String, Object> parameters = new HashMap<>();
                parameters.put("id", first.getId());
                commandRequest.setParameters(parameters);
            }


        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        return commandRequest;
    }
}
