package commands;

import managers.commandManager.CommandManager;
import commands.request.CommandRequest;

public abstract class Command {

    private String target;
    private String description;
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public abstract CommandRequest execute(CommandManager commandManager, String arguments);

}
