package commands.interactives.show;

import commands.interactives.utills.ShowSpacesCharacters;
import models.LabWork;

import java.time.format.DateTimeFormatter;

public class ShowInteractiveLabWork {

    public static void showLabWork(LabWork labWork, int levelSpace) {

        ShowSpacesCharacters.showSpace(levelSpace);
        if (labWork.getId() == null) {
            System.out.println("ID: отсутствует");
        }
        else {
            System.out.printf("ID: %d", labWork.getId());
            System.out.println();

        }


        ShowSpacesCharacters.showSpace(levelSpace);
        if (labWork.getCreationDate() == null) {
            System.out.println("Дата создания: отсутствует");
        } else {
            System.out.printf("Дата создания: %s", labWork.getCreationDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            System.out.println();
        }

        ShowSpacesCharacters.showSpace(levelSpace);
        if (labWork.getName() == null) {
            System.out.println("Название лабораторной работы: отсутствует");
        }
        else {
            System.out.printf("Название лабораторной работы: %s", labWork.getName());
            System.out.println();
        }


        ShowSpacesCharacters.showSpace(levelSpace);
        if (labWork.getCoordinates() == null) {
            System.out.println("Информация о координатах: отсутствует");
        } else {
            System.out.println("Информация о координатах:");
            ShowInteractiveCoordinates.coordinatesShow(labWork.getCoordinates(), levelSpace + 1);
        }

        ShowSpacesCharacters.showSpace(levelSpace);
        System.out.printf("Значение минимальной точки: %d", labWork.getMinimalPoint());
        System.out.println();

        ShowSpacesCharacters.showSpace(levelSpace);
        if (labWork.getMaximumPoint() == null) {
            System.out.println("Значение максимальной точки: отсутствует");
        } else {
            System.out.printf("Значение максимальной точки: %d", labWork.getMaximumPoint());
            System.out.println();
        }

        ShowSpacesCharacters.showSpace(levelSpace);
        if (labWork.getDifficulty() == null) {
            System.out.println("Сложность лабораторной работы: отсутствует");
        } else {
            System.out.printf("Сложность лабораторной работы: %s", labWork.getDifficulty().getTitle());
            System.out.println();

        }

        ShowSpacesCharacters.showSpace(levelSpace);
        if (labWork.getAuthor() == null) {
            System.out.println("Информация об авторе: отсутствует");
        } else {

            System.out.println("Информация об авторе:");
            ShowInteractivePerson.showPerson(labWork.getAuthor(), levelSpace + 1);
            System.out.println();

        }

    }

}
