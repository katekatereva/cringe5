package commands.interactives.add;

import commands.interactives.utills.QuestionInteractive;
import models.Country;
import models.Location;
import models.Person;

import java.util.Scanner;

public class AddInteractivePerson {


    public static void interactivePerson(Person author, Scanner scanner) {
        AddInteractivePerson.interactivePersonName(author, scanner);

        if (QuestionInteractive.yesOrNoQuestion("Вы хотите добавить день рождения автора?", scanner)) {
            AddInteractiveBirthDay.interactiveBirthDay(author, scanner);
        }

        AddInteractivePerson.interactivePersonEyeColor(author, scanner);

        if (QuestionInteractive.yesOrNoQuestion("Вы хотите добавить цвет волос у автора?", scanner)) {
            AddInteractivePerson.interactivePersonHairColor(author, scanner);
        }

        if (QuestionInteractive.yesOrNoQuestion("Вы хотите добавить национальность для автора?", scanner)) {
            AddInteractivePerson.interactivePersonNationality(author, scanner);
        }

        Location location = new Location();
        AddInteractiveLocation.interactiveLocation(location, scanner);
        author.setLocation(location);
    }

    public static void interactivePersonName(Person author, Scanner scanner) {
        System.out.print("Введите имя автора: ");
        while (!author.setName(scanner.nextLine())) {
            System.out.println("Имя автора должно быть не пустым");
            System.out.print("Введите имя автора: ");
        }
    }

    public static void interactivePersonEyeColor(Person author, Scanner scanner) {
        models.eye.Color[] eyeColors = models.eye.Color.values();

        for (int i = 0; i < eyeColors.length; i++) {
            System.out.printf("%d. %s", i + 1, eyeColors[i]);
            System.out.println();
        }

        while (true) {

            try {
                System.out.printf("Выберите номер от 1 до %d, чтобы выбрать цвет глаз: ", eyeColors.length);
                int positionOfEyeColor = Integer.parseInt(scanner.nextLine());
                if (positionOfEyeColor >= 1 && positionOfEyeColor <= eyeColors.length) {
                    author.setEyeColor(eyeColors[positionOfEyeColor - 1]);
                    break;
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Введите число");
            }

        }
    }

    public static void interactivePersonHairColor(Person author, Scanner scanner) {
        models.hair.Color[] hairColors = models.hair.Color.values();

        for (int i = 0; i < hairColors.length; i++) {
            System.out.printf("%d. %s", i + 1, hairColors[i]);
            System.out.println();
        }

        while (true) {

            try {
                System.out.printf("Выберите номер от 1 до %d, чтобы выбрать цвет волос: ", hairColors.length);
                int positionOfHairColor = Integer.parseInt(scanner.nextLine());
                if (positionOfHairColor >= 1 && positionOfHairColor <= hairColors.length) {
                    author.setHairColor(hairColors[positionOfHairColor - 1]);
                    break;
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Введите число");
            }

        }

    }

    public static void interactivePersonNationality(Person author, Scanner scanner) {
        Country[] countries = Country.values();

        for (int i = 0; i < countries.length; i++) {
            System.out.printf("%d. %s", i + 1, countries[i]);
            System.out.println();
        }

        while (true) {

            try {
                System.out.printf("Выберите номер от 1 до %d, чтобы выбрать национальность: ", countries.length);
                int positionOfCountry = Integer.parseInt(scanner.nextLine());
                if (positionOfCountry >= 1 && positionOfCountry <= countries.length) {
                    author.setNationality(countries[positionOfCountry - 1]);
                    break;
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Введите число");
            }

        }
    }

}