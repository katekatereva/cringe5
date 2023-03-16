package commands;

import commands.request.RequestType;
import commands.response.CommandResponse;
import commands.interactives.show.ShowInteractiveLabWork;
import managers.commandManager.CommandManager;
import commands.request.CommandRequest;
import models.LabWork;

import java.util.ArrayDeque;

public class ShowCommand extends Command{

    public ShowCommand() {
        setTarget("show");
        setDescription("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
    }

    @Override
    public CommandRequest execute(CommandManager commandManager, String arguments) {
        CommandRequest commandRequest = new CommandRequest();
        commandRequest.setRequestType(RequestType.GET);

        try {
            CommandResponse commandResponse = commandManager.getResponse(commandRequest);

            Class typeOfObjectInResponse = commandResponse.getTypeOfObject();
            if (typeOfObjectInResponse == ArrayDeque.class) {
                ArrayDeque<LabWork> labWorks = (ArrayDeque<LabWork>) commandResponse.getObject();


                if (labWorks.size() > 0) {
                    System.out.println("---------------------------------------------------------------------------");
                    for (LabWork labWork : labWorks) {
                        ShowInteractiveLabWork.showLabWork(labWork, 0);
                        System.out.println("---------------------------------------------------------------------------");
                    }
                }


                else {
                    System.out.println("В списке нет лабораторных работ");
                }



            }
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        commandRequest.setRequestType(RequestType.INTERNAL);
        return commandRequest;
    }
}
