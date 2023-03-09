package managers.commandManager;

import commands.Command;
import commands.request.CommandRequest;
import commands.request.RequestType;
import commands.response.CommandResponse;
import commands.response.ResponseType;
import commands.utills.CommandSplitService;
import dao.DAO;
import dao.LabWorkFileDAO;
import models.LabWork;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandManager {
    private Map<String, Command> commands;
    private Scanner scanner;
    private final DAO<LabWork> dao;

    public CommandManager() {
        this.commands = new HashMap<>();
        this.dao = new LabWorkFileDAO();
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
    public void addCommand(Command command) {
        commands.put(command.getTitle(), command);
    }

    public Map<String, Command> getCommands() {
        return new HashMap<>(commands);
    }


    public CommandResponse handleCommandType(String commandType) throws CloneNotSupportedException, NoSuchFieldException {

        String[] commandSplit = CommandSplitService.splitCommandType(commandType);

        String commandName = commandSplit[0];
        String commandArguments = commandSplit[1];

        if (commands.containsKey(commandName)) {

            CommandRequest commandRequest = commands.get(commandName).execute(this, commandArguments);
            return getResponse(commandRequest);
        }

        return null;

    }

    public CommandResponse getResponse(CommandRequest commandRequest) throws CloneNotSupportedException, NoSuchFieldException {

        CommandResponse commandResponse = new CommandResponse();
        if (commandRequest.getRequestType() == RequestType.INTERNAL) {
            commandResponse.setResponseType(ResponseType.NONE);
        }

        else {

            switch (commandRequest.getRequestType()) {
                case CREATE -> {
                    dao.create((LabWork) commandRequest.getObject());
                    commandResponse.setObject(commandRequest.getObject());
                }
                case GET -> {
                    if (commandRequest.getParameters() == null) {
                        commandResponse.setObject(dao.getAll());
                        commandResponse.setTypeOfObject(ArrayDeque.class);
                    }
                }
                case DELETE -> {
                    Map<String, Object> parameters = commandRequest.getParameters();

                    if (commandRequest.getParameters() != null) {

                        ArrayDeque<LabWork> labWorks = dao.getAll();

                        for (LabWork labWork : labWorks) {

                            boolean isDelete = true;
                            for (Map.Entry<String, Object> parameter : parameters.entrySet()) {
                                Field field = labWork.getClass().getDeclaredField(parameter.getKey());
                                field.setAccessible(true);
                                try {
                                    if (field.get(labWork) != parameter.getValue()) {
                                        isDelete = false;
                                        break;
                                    }
                                } catch (IllegalAccessException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                            if (isDelete) {
                                dao.delete(labWork);
                            }
                        }

                    }
                }
            }

        }

        return commandResponse;

    }

}