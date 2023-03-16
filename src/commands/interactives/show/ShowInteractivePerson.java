package commands.interactives.show;

import commands.interactives.utills.ShowSpacesCharacters;
import models.Person;

import java.time.format.DateTimeFormatter;

public class ShowInteractivePerson {

    public static void showPerson(Person person, int levelSpace) {

        ShowSpacesCharacters.showSpace(levelSpace);

        if (person.getName() == null) {
            System.out.println("Имя автора: отсутствует");
        } else {
            System.out.printf("Имя автора: %s", person.getName());
            System.out.println();
        }


        ShowSpacesCharacters.showSpace(levelSpace);
        if (person.getBirthday() == null) {
            System.out.println("День рождения: отсутствует");
        } else {
            System.out.printf("День рождения: %s", person.getBirthday().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
            System.out.println();
        }


        ShowSpacesCharacters.showSpace(levelSpace);
        if (person.getEyeColor() == null) {
            System.out.println("Цвет глаз: отсутствует");
        } else {
            System.out.printf("Цвет глаз: %s", person.getEyeColor().getTitle());
            System.out.println();

        }

        ShowSpacesCharacters.showSpace(levelSpace);
        if (person.getHairColor() == null) {
            System.out.println("Цвет волос: отсутствует");
        } else {
            System.out.printf("Цвет волос: %s", person.getHairColor().getTitle());
            System.out.println();

        }

        ShowSpacesCharacters.showSpace(levelSpace);
        if (person.getNationality() == null) {
            System.out.println("Национальность: отсутствует");
        } else {
            System.out.printf("Национальность: %s", person.getNationality());
            System.out.println();

        }

        ShowSpacesCharacters.showSpace(levelSpace);
        if (person.getLocation() == null) {
            System.out.println("Информация о локации: отсутствует");
        } else {

            System.out.println("Информация о локации:");
            ShowInteractiveLocation.showLocation(person.getLocation(), levelSpace + 1);

        }


    }

}
