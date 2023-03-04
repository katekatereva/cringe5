package commands;

import models.LabWork;

import java.util.Scanner;

public class AddCommand extends Command{

    public AddCommand(Scanner scanner) {
        setTitle("add");
        setDescription("добавить новый элемент в коллекцию");
    }

    @Override
    public void execute() {
        LabWork labWork = new LabWork();

    }
}
