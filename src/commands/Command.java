package commands;

import managers.commandManager.CommandManager;
import commands.request.CommandRequest;

public abstract class Command {

    private String title;
    private String description;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract CommandRequest execute(CommandManager commandManager, String arguments) throws CloneNotSupportedException;

}
