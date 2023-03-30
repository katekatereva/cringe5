package ru.itmo.commands;

import ru.itmo.Pam;
import ru.itmo.commands.request.CommandRequest;
import ru.itmo.commands.request.RequestType;
import ru.itmo.managers.commandManager.CommandManager;

public class ExitCommand extends Command{


    public ExitCommand() {
        setTargetTitleForUserInput("exit");
        setDescription("exit : завершить программу (без сохранения в файл)");
    }

    @Override
    public CommandRequest execute(CommandManager commandManager, String arguments) {
        CommandRequest commandRequest = new CommandRequest();
        commandRequest.setRequestType(RequestType.INTERNAL);
        Pam.exit();
        return commandRequest;
    }
}
