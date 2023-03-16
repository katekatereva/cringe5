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
import java.util.*;

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
        commands.put(command.getTarget(), command);
    }

    public Map<String, Command> getCommands() {
        return new HashMap<>(commands);
    }


    public CommandResponse handleCommandType(String commandType) throws NoSuchFieldException {

        String[] commandSplit = CommandSplitService.splitCommandType(commandType);

        String commandName = commandSplit[0];
        String commandArguments = commandSplit[1];

        if (commands.containsKey(commandName)) {

            CommandRequest commandRequest = commands.get(commandName).execute(this, commandArguments);
            return getResponse(commandRequest);
        }

        return null;

    }


    private void handleRequestType(CommandRequest commandRequest, CommandResponse commandResponse) throws NoSuchFieldException {
        switch (commandRequest.getRequestType()) {
            case CREATE -> {
                dao.create((LabWork) commandRequest.getObject());
                commandResponse.setObject(commandRequest.getObject());
                commandResponse.setResponseType(ResponseType.OK);

//
//                for (Map.Entry<Integer, Integer> entry : dao.getHashList().entrySet()){
//                    System.out.printf("%d %d", entry.getKey(), entry.getValue());
//                    System.out.println();
//                }

            }
            case GET -> {
                if (commandRequest.getParameters() == null) {
                    commandResponse.setObject(dao.getAll());
                    commandResponse.setTypeOfObject(ArrayDeque.class);
                    commandResponse.setResponseType(ResponseType.OK);
                } else {
                    Map<String, Object> parameters = commandRequest.getParameters();

                    ArrayDeque<LabWork> labWorks = dao.getAll();

                    ArrayDeque<LabWork> getLabWorks = new ArrayDeque<>();

                    for (LabWork labWork : labWorks) {

                        boolean isGet = true;

                        for (Map.Entry<String, Object> parameter : parameters.entrySet()) {
                            Field field = labWork.getClass().getDeclaredField(parameter.getKey());
                            field.setAccessible(true);

                            try {
                                if (field.get(labWork) != parameter.getValue()) {
                                    isGet = false;
                                    break;
                                }
                            } catch (IllegalAccessException e) {
                                commandResponse.setResponseType(ResponseType.MANAGER_ERROR);
                                return;
                            }

                        }

                        if (isGet) {
                            getLabWorks.addLast(new LabWork(labWork));
                        }

                    }

                    commandResponse.setObject(getLabWorks);
                    commandResponse.setTypeOfObject(ArrayDeque.class);
                    commandResponse.setResponseType(ResponseType.OK);



                }
            }
            case GET_FIRST -> {
                try {
                    commandResponse.setObject(dao.getFirst());
                    commandResponse.setTypeOfObject(LabWork.class);
                    commandResponse.setResponseType(ResponseType.OK);
                } catch (NoSuchElementException noSuchElementException) {
                    commandResponse.setResponseType(ResponseType.NOT_FOUND);
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
                                commandResponse.setResponseType(ResponseType.MANAGER_ERROR);
                                return;
                            }
                        }

                        if (isDelete) {
                            dao.delete(labWork);
                        }
                    }

                } else {
                    dao.clear();
                }
                commandResponse.setResponseType(ResponseType.OK);

            }

            case UPDATE -> {
                LabWork labWork = (LabWork) commandRequest.getObject();
                dao.update(labWork.getId(), labWork);
                commandResponse.setResponseType(ResponseType.OK);

            }
        }

    }


    public CommandResponse getResponse(CommandRequest commandRequest) throws NoSuchFieldException{

        if (commandRequest == null) {
            return null;
        }

        CommandResponse commandResponse = new CommandResponse();
        if (commandRequest.getRequestType() == RequestType.INTERNAL) {
            commandResponse.setResponseType(ResponseType.NONE);
        }

        else {
            handleRequestType(commandRequest, commandResponse);
        }

        return commandResponse;

    }

}