package client;

import commands.*;
import commands.response.CommandResponse;
import commands.response.ResponseType;
import managers.commandManager.CommandManager;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Pam {

    private static boolean isActiveClient = true;

    public static void exit() {
        Pam.isActiveClient = false;
    }

    public static void main(String[] args)  {

        Scanner scanner = new Scanner(System.in);

        Command helpCommand = new HelpCommand();
        Command addCommand = new AddCommand(scanner);
        Command showCommand = new ShowCommand();
        Command removeByIdCommand = new RemoveByIdCommand(scanner);
        Command exitCommand = new ExitCommand();
        Command updateCommand = new UpdateCommand(scanner);
        Command clearCommand = new ClearCommand();
        Command removeHeadCommand = new RemoveHeadCommand();

        CommandManager commandManager = new CommandManager();

        commandManager.setScanner(scanner);

        commandManager.addCommand(showCommand);
        commandManager.addCommand(helpCommand);
        commandManager.addCommand(addCommand);
        commandManager.addCommand(removeByIdCommand);
        commandManager.addCommand(exitCommand);
        commandManager.addCommand(updateCommand);
        commandManager.addCommand(clearCommand);
        commandManager.addCommand(removeHeadCommand);


        while (isActiveClient) {

            try {
                System.out.print("Введите команду: ");
                String commandType = scanner.nextLine();

                if (!commandType.isBlank()) {
                    CommandResponse commandResponse = commandManager.handleCommandType(commandType);

                    if (commandResponse == null) {
                        System.out.println("Такая команда не найдена");
                    }

                    else {

                        if (commandResponse.getResponseType() == ResponseType.OK) {
                            System.out.println("Команда успешно выполнена");
                        } else if (commandResponse.getResponseType() == ResponseType.BAD_REQUEST) {
                            System.out.println("Менеджер не смог обработать запрос");
                        } else if (commandResponse.getResponseType() == ResponseType.MANAGER_ERROR) {
                            System.out.println("Во время работы менеджера произошла ошибка");
                        } else if (commandResponse.getResponseType() == ResponseType.NOT_FOUND) {
                            System.out.println("Работа не найдена");
                        }

                    }
                }

            }
            catch(NoSuchElementException exception) {
                break;
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }

            // NoSuchElementException
            // ArrayIndexOutOfBoundsException
            // IOException
            // InputMismatchException
            // NullPointerException

        }
    }
}