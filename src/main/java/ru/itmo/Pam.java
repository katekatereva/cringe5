package ru.itmo;


import ru.itmo.commands.Command;
import ru.itmo.commands.*;
import ru.itmo.commands.response.CommandResponse;
import ru.itmo.commands.response.ResponseType;
import ru.itmo.managers.commandManager.CommandManager;


import java.util.NoSuchElementException;
import java.util.Scanner;
public class Pam {

    private static boolean isActiveClient = true;


    public static void exit() {
        Pam.isActiveClient = false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        CommandManager commandManager = new CommandManager();


        Command helpCommand = new HelpCommand();
        Command addCommand = new AddCommand(scanner);
        Command showCommand = new ShowCommand();
        Command removeByIdCommand = new RemoveByIdCommand(scanner);
        Command exitCommand = new ExitCommand();
        Command updateCommand = new UpdateCommand(scanner);
        Command clearCommand = new ClearCommand();
        Command removeHeadCommand = new RemoveHeadCommand();
        Command getMinCommand = new GetMinCommand();
        Command saveCommand = new SaveCommand();
        Command filterByAuthorCommand = new FilterByAuthorCommand(scanner);
        Command infoCommand = new InfoCommand();
        Command removeAnyByDifficultyCommand = new RemoveAnyByDifficultyCommand(scanner);
        Command removeGreaterCommand = new RemoveGreaterCommand(scanner);


        commandManager.setScanner(scanner);

        commandManager.addCommand(showCommand);
        commandManager.addCommand(helpCommand);
        commandManager.addCommand(addCommand);
        commandManager.addCommand(removeByIdCommand);
        commandManager.addCommand(exitCommand);
        commandManager.addCommand(updateCommand);
        commandManager.addCommand(clearCommand);
        commandManager.addCommand(removeHeadCommand);
        commandManager.addCommand(getMinCommand);
        commandManager.addCommand(saveCommand);
        commandManager.addCommand(filterByAuthorCommand);
        commandManager.addCommand(infoCommand);
        commandManager.addCommand(removeAnyByDifficultyCommand);
        commandManager.addCommand(removeGreaterCommand);


        try {

            if (!commandManager.initialDataManager()) {
                exit();
            }

            if (!commandManager.importData()) {
                exit();
            }



            while (isActiveClient) {


                System.out.print("Введите команду: ");
                String commandType = scanner.nextLine();

                if (!commandType.isBlank()) {
                    CommandResponse commandResponse = commandManager.handleCommandType(commandType);

                    if (commandResponse == null) {
                        System.out.println("Такая команда не найдена");
                    } else {

                        if (commandResponse.getResponseType() == ResponseType.OK) {
                            System.out.println("Команда успешно выполнена");
                        }
                        else if (commandResponse.getResponseType() == ResponseType.BAD_REQUEST) {
                            System.out.println("Менеджер не смог обработать запрос");
                        } else if (commandResponse.getResponseType() == ResponseType.MANAGER_ERROR) {
                            System.out.println("Во время работы менеджера произошла ошибка");
                        } else if (commandResponse.getResponseType() == ResponseType.NOT_FOUND) {
                            System.out.println("Работа не найдена");
                        }

                    }
                }


            }
        } catch (NoSuchElementException ignored) { }

    }
}
