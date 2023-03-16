package commands;

import commands.interactives.add.*;
import commands.interactives.utills.QuestionInteractive;
import commands.request.RequestType;
import managers.commandManager.CommandManager;
import commands.request.CommandRequest;
import managers.commandManager.utils.GeneratorID;
import models.*;

import java.time.LocalDate;
import java.util.Scanner;

public class AddCommand extends Command {

    private Scanner scanner;

    public AddCommand(Scanner scanner) {
        setTarget("add");
        setDescription("add {element} : добавить новый элемент в коллекцию");
        this.scanner = scanner;
    }

    @Override
    public CommandRequest execute(CommandManager commandManager, String arguments) {

        LabWork labWork = new LabWork();

        AddInteractiveLabWork.interactiveName(labWork, scanner);

        Coordinates coordinates = new Coordinates();

        AddInteractiveCoordinate.interactiveX(coordinates, scanner);
        AddInteractiveCoordinate.interactiveY(coordinates, scanner);

        labWork.setCoordinates(coordinates);

        AddInteractiveLabWork.interactiveMinimalPoint(labWork, scanner);
        AddInteractiveLabWork.interactiveMaximumPoint(labWork, scanner);


        if (QuestionInteractive.yesOrNoQuestion("Вы хотите добавить сложность работы?", scanner)) {
            AddInteractiveLabWork.interactiveDifficulty(labWork, scanner);
        }

        if (QuestionInteractive.yesOrNoQuestion("Вы хотите добавить автора?", scanner)) {
            Person author = new Person();
            AddInteractivePerson.interactivePerson(author, scanner);
            labWork.setAuthor(author);
        }

        CommandRequest commandRequest = new CommandRequest();

        commandRequest.setRequestType(RequestType.CREATE);
        commandRequest.setObject(labWork);
        commandRequest.setParameters(null);

        return commandRequest;
    }
}
