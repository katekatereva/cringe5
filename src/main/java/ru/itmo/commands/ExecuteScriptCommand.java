package ru.itmo.commands;

import ru.itmo.commands.request.CommandRequest;
import ru.itmo.managers.commandManager.CommandManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ExecuteScriptCommand extends Command{

    private Scanner scanner;
    public ExecuteScriptCommand(Scanner scanner) {
        this.scanner = scanner;
        setTargetTitleForUserInput("execute_script");
        setDescription("execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
    }


    @Override
    public CommandRequest execute(CommandManager commandManager, String arguments) {
        CommandRequest commandRequest = new CommandRequest();
        System.out.println("Введите путь до файла со скриптом: ");
        String pathToFileWithScript = scanner.nextLine();

        ArrayList<String> executedFiles = new ArrayList<>();

        Path file = Paths.get(pathToFileWithScript);


        if (Files.exists(file) && Files.isReadable(file)) {
            executedFiles.add(pathToFileWithScript);
            try (Scanner scannerForFile = new Scanner(file.toFile())) {
                while (scannerForFile.hasNext()) {
                    String command = scannerForFile.nextLine();
                    commandManager.handleCommandType(command);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Во время чтения файла со скриптами произошла ошибка");
            }
        }

        else {
            System.out.println("Программа не смогла получить доступ к файлу для чтения");
        }

        return commandRequest;

    }
}
