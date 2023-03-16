package commands;

import client.Pam;
import commands.request.CommandRequest;
import commands.request.RequestType;
import managers.commandManager.CommandManager;

public class ExitCommand extends Command{


    public ExitCommand() {
        setTarget("exit");
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
