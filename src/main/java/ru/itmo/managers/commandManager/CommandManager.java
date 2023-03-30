package ru.itmo.managers.commandManager;



import ru.itmo.commands.Command;
import ru.itmo.commands.interactives.utills.QuestionInteractive;
import ru.itmo.commands.request.CommandRequest;
import ru.itmo.commands.request.RequestType;
import ru.itmo.commands.response.CommandResponse;
import ru.itmo.commands.response.ResponseType;
import ru.itmo.commands.utills.CommandSplitService;
import ru.itmo.dao.DAO;
import ru.itmo.dao.LabWorkFileDAO;
import ru.itmo.managers.dataManager.DataManager;
import ru.itmo.managers.dataManager.FileDataManager;
import ru.itmo.managers.dataManager.response.DataResponse;
import ru.itmo.managers.dataManager.response.DataResponseType;
import ru.itmo.models.LabWork;
import ru.itmo.models.Person;

import java.lang.reflect.Field;
import java.util.*;

public class CommandManager {
    private Map<String, Command> commands;
    private Scanner scanner;
    private DAO<LabWork> dao;
    private DataManager<LabWork> dataManager;

    public CommandManager(Map<String, Command> commands) {
        this.commands = commands;
        this.dao = new LabWorkFileDAO();
    }

    public CommandManager() {
        this.commands = new HashMap<>();
        this.dao = new LabWorkFileDAO();
    }

    public boolean initialDataManager() {

        String pathToFile = System.getenv("PATH_TO_COLLECTION");
        if (pathToFile == null) {
            System.out.println("Программа не смогла получить доступ к переменной окружения");
            return false;
        }
        this.dataManager = new FileDataManager(pathToFile);
        return true;
    }



    private boolean isRecreateFile() {
        if (QuestionInteractive.yesOrNoQuestion("Хотите попробовать пересоздать файл?", scanner)) {
            if (dataManager.recreateFile()) {
                System.out.println("Файл успешно пересоздан");
                return true;
            }
            else {
                System.out.println("Не удалось пересоздать файл");
                return false;
            }
        }
        return false;
    }

    public boolean importData() {
        DataResponse dataResponse = dataManager.importData();
        if (dataResponse.getResponseType() == DataResponseType.PERMISSION_READ_DENIED) {
            System.out.println("Программа не смогла получить доступ к файлу для чтения");
            return false;
        }
        else if (dataResponse.getResponseType() == DataResponseType.BAD_FILE) {
            System.out.println("Данные были повреждены или хранятся в неправильном формате");
            isRecreateFile();
        }
        this.dao = new LabWorkFileDAO(dataResponse.getLabWorks());
        return true;
    }

    public boolean exportData() {
        DataResponse dataResponse = dataManager.exportData(dao.getAll());

        if (dataResponse.getResponseType() == DataResponseType.PERMISSION_WRITE_DENIED) {
            System.out.println("Программа не смогла получить доступ к файлу для записи");
            return false;
        } else if (dataResponse.getResponseType() == DataResponseType.BAD_DATA) {
            System.out.println("Программа не смогла записать данные в файл");
            return false;
        }
        return true;
    }


    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
    public void addCommand(Command command) {
        commands.put(command.getTargetTitleForUserInput(), command);
    }
    public Map<String, Command> getCommands() {
        return new HashMap<>(commands);
    }
    public CommandResponse handleCommandType(String commandType){

        String[] commandSplit = CommandSplitService.splitCommandType(commandType);

        String commandName = commandSplit[0];
        String commandArguments = commandSplit[1];

        if (commands.containsKey(commandName)) {

            CommandRequest commandRequest = commands.get(commandName).execute(this, commandArguments);
            return getResponse(commandRequest);
        }

        return null;

    }


    private void handleRequestType(CommandRequest commandRequest, CommandResponse commandResponse)  {
        switch (commandRequest.getRequestType()) {
            case CREATE -> {
                dao.create((LabWork) commandRequest.getObject());
                commandResponse.setObject(commandRequest.getObject());
                commandResponse.setResponseType(ResponseType.OK);

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
                            Field field = null;
                            try {
                                field = labWork.getClass().getDeclaredField(parameter.getKey());
                            } catch (NoSuchFieldException e) {
                                commandResponse.setResponseType(ResponseType.MANAGER_ERROR);
                                return;
                            }
                            field.setAccessible(true);


                            try {

                                if (Objects.equals(parameter.getKey(), "author")) {
                                    Person author = labWork.getAuthor();

                                    if (author == null) {
                                        isGet = false;
                                        break;
                                    }

                                    if (!Objects.equals(author.getName(), (String) parameter.getValue())) {
                                        isGet = false;
                                        break;
                                    }

                                }
                                else {
                                    if (field.get(labWork) != parameter.getValue()) {
                                        isGet = false;
                                        break;
                                    }
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
                            Field field = null;
                            try {
                                field = labWork.getClass().getDeclaredField(parameter.getKey());
                            } catch (NoSuchFieldException e) {
                                commandResponse.setResponseType(ResponseType.MANAGER_ERROR);
                                return;
                            }
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

            case GET_MIN -> {
                LabWork labWork = dao.getMin();
                if (labWork == null) {
                    commandResponse.setResponseType(ResponseType.NOT_FOUND);

                }
                else {
                    commandResponse.setResponseType(ResponseType.OK);
                    commandResponse.setTypeOfObject(LabWork.class);
                    commandResponse.setObject(labWork);
                }

            }
            case SAVE -> {
                if (!exportData()) {
                    commandResponse.setResponseType(ResponseType.MANAGER_ERROR);
                } else {
                    commandResponse.setResponseType(ResponseType.OK);
                }
            }
            case INFO -> {
                HashMap<String, Object> parameters = new HashMap<>();
                parameters.put("type", dao.getAll().getClass());
                parameters.put("date_initial", dataManager.getDateInitial());
                parameters.put("count", dao.getAll().size());
                commandResponse.setParameters(parameters);
                commandResponse.setResponseType(ResponseType.OK);

            } case DELETE_GREATER -> {
                LabWork lowLabwork = (LabWork) commandRequest.getObject();
                ArrayDeque<LabWork> labWorks = dao.getAll();

                for (LabWork labWork : labWorks) {

                    if (labWork.getName().length() > lowLabwork.getName().length()) {
                        dao.delete(labWork.getId());
                    }

                }
                commandResponse.setResponseType(ResponseType.OK);


            }
        }

    }


    public CommandResponse getResponse(CommandRequest commandRequest){

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
