package commandManager;

import commands.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandManager {
    private Map<String, Command> commands;
    private Scanner scanner;

    public CommandManager() {
        this.commands = new HashMap<>();
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

}
