package commands;

import commands.request.RequestType;
import managers.commandManager.CommandManager;
import commands.request.CommandRequest;
import models.*;

import java.time.LocalDate;
import java.util.Scanner;

public class AddCommand extends Command{

    public AddCommand(Scanner scanner) {
        setTitle("add");
        setDescription("добавить новый элемент в коллекцию");
    }

    @Override
    public CommandRequest execute(CommandManager commandManager, String arguments) throws CloneNotSupportedException {

        LabWork labWork = new LabWork();



        Coordinates coordinates = new Coordinates();
        coordinates.setY(15L);
        coordinates.setX(3.4f);
        Person author = new Person();
        author.setName("Ivan");
        author.setBirthday(LocalDate.now());
        author.setEyeColor(models.eye.Color.BLACK);
        author.setHairColor(models.hair.Color.GREEN);
        author.setNationality(Country.RUSSIA);

        Location location = new Location();
        location.setX(45L);
        location.setY(43);
        location.setZ(3L);
        author.setLocation(location);

        labWork.setName("Laba 1");
        labWork.setCoordinates(coordinates);
        labWork.setMaximumPoint(15);
        labWork.setMinimalPoint(23);
        labWork.setDifficulty(Difficulty.INSANE);
        labWork.setAuthor(author);

        CommandRequest commandRequest = new CommandRequest();

        commandRequest.setRequestType(RequestType.CREATE);
        commandRequest.setObject(labWork);
        commandRequest.setParameters(null);

        return commandRequest;
    }
}