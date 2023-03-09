import commands.AddCommand;
import commands.Command;
import commands.HelpCommand;
import commands.ShowCommand;
import commands.response.CommandResponse;
import commands.response.ResponseType;
import commands.RemoveByIdCommand;
import managers.commandManager.CommandManager;
import models.*;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Pam {

    public static void main(String[] args) throws CloneNotSupportedException {




        Scanner scanner = new Scanner(System.in);

        Command helpCommand = new HelpCommand();
        Command addCommand = new AddCommand(scanner);
        Command showCommand = new ShowCommand();
        Command removeByIdCommand = new RemoveByIdCommand(scanner);

        CommandManager commandManager = new CommandManager();

        commandManager.setScanner(scanner);

        commandManager.addCommand(showCommand);
        commandManager.addCommand(helpCommand);
        commandManager.addCommand(addCommand);
        commandManager.addCommand(removeByIdCommand);


        while (true) {

            try {
                System.out.print("Введите команду: ");
                String commandType = scanner.nextLine();

                if (!commandType.isBlank()) {
                    CommandResponse commandResponse = commandManager.handleCommandType(commandType);

                    if (commandResponse == null) {
                        System.out.println("Такая команда не найдена.");
                    }

                    else {

                        if (commandResponse.getResponseType() != ResponseType.NONE) {
                            Class typeOfObjectInResponse = commandResponse.getTypeOfObject();
                            if (typeOfObjectInResponse == ArrayDeque.class) {
                                ArrayDeque<LabWork> labWorks = (ArrayDeque<LabWork>) commandResponse.getObject();

                                for (LabWork labWork : labWorks) {
                                    System.out.printf("ID: %d", labWork.getId());
                                    System.out.println();
                                    System.out.printf("Name: %s", labWork.getName());
                                    System.out.println();
                                    System.out.println();
                                }

                            }
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