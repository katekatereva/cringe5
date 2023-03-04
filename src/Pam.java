import commandManager.CommandManager;
import commands.Command;
import commands.ShowCommand;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Pam {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CommandManager commandManager = new CommandManager();

        commandManager.setScanner(scanner);

        ShowCommand showCommand = new ShowCommand();
        commandManager.addCommand(showCommand);


        while (true) {

            try {

                String commandType = scanner.nextLine();

                if (commandType.equals("help")) {
                    commandManager.getCommands();
                    for (Map.Entry<String, Command> command : commandManager.getCommands().entrySet()) {
                        System.out.printf("%s : %s", command.getValue().getTitle(), command.getValue().getDescription());
                        System.out.println();

                    }
                }
            }
            catch(NoSuchElementException exception) {
                break;
            }

            // NoSuchElementException
            // ArrayIndexOutOfBoundsException
            // IOException
            // InputMismatchException
            // NullPointerException

        }
    }
}
