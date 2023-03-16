package commands.interactives.update;

import commands.interactives.add.AddInteractiveBirthDay;
import commands.interactives.add.AddInteractivePerson;
import commands.interactives.show.ShowInteractivePerson;
import commands.interactives.utills.QuestionInteractive;
import models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UpdateInteractivePerson {

    public static Person updatePerson(Person author, Scanner scanner) {

        List<String> pointsOfPerson = new ArrayList<>();

        pointsOfPerson.add("Имя автора");
        pointsOfPerson.add("День рождение");
        pointsOfPerson.add("Цвет глаз");
        pointsOfPerson.add("Цвет волос");
        pointsOfPerson.add("Национальность");
        pointsOfPerson.add("Информация о локации");



        boolean isUpdate = true;
        while (isUpdate) {
            try {
                System.out.println("---------------------------------------------------------------------------");
                ShowInteractivePerson.showPerson(author, 0);
                System.out.println("---------------------------------------------------------------------------");


                for (int i = 0; i < pointsOfPerson.size(); i++) {
                    System.out.printf("%d. %s", i + 1, pointsOfPerson.get(i));
                    System.out.println();
                }
                System.out.print("Введите номер пункта, который вы хотите изменить или введите 0, " +
                        "чтобы завершить обновление: ");
                int point = Integer.parseInt(scanner.nextLine());

                switch (point) {
                    case 0 -> {
                        isUpdate = false;
                    }
                    case 1 -> {
                        AddInteractivePerson.interactivePersonName(author, scanner);
                    }
                    case 2 -> {
                        if (author.getBirthday() == null) {
                            if (QuestionInteractive.yesOrNoQuestion("Вы хотите добавить день рождения автора?", scanner)) {
                                AddInteractiveBirthDay.interactiveBirthDay(author, scanner);
                            }
                        }
                        else {
                            if (QuestionInteractive.yesOrNoQuestion("Вы хотите изменить день рождения автора?", scanner)) {
                                UpdateInteractiveBirthDay.updateBirthDay(author, scanner);
                            } else if (QuestionInteractive.yesOrNoQuestion("Вы хотите удалить день рождения автора?", scanner)) {
                                author.setBirthday(null);
                            }
                        }
                    }

                    case 3 -> {
                        AddInteractivePerson.interactivePersonEyeColor(author, scanner);
                    }

                    case 4 -> {
                        if (author.getHairColor() == null) {
                            if (QuestionInteractive.yesOrNoQuestion("Вы хотите добавить цвет волос автора?", scanner)) {
                                AddInteractivePerson.interactivePersonHairColor(author, scanner);
                            }
                        }
                        else {
                            if (QuestionInteractive.yesOrNoQuestion("Вы хотите изменить цвет волос автора?", scanner)) {
                                AddInteractivePerson.interactivePersonHairColor(author, scanner);
                            } else if (QuestionInteractive.yesOrNoQuestion("Вы хотите удалить цвет волос автора?", scanner)) {
                                author.setHairColor(null);
                            }
                        }
                    }

                    case 5 -> {
                        if (author.getNationality() == null) {
                            if (QuestionInteractive.yesOrNoQuestion("Вы хотите добавить национальность автора?", scanner)) {
                                AddInteractivePerson.interactivePersonNationality(author, scanner);
                            }
                        }
                        else {
                            if (QuestionInteractive.yesOrNoQuestion("Вы хотите изменить национальность автора?", scanner)) {
                                AddInteractivePerson.interactivePersonNationality(author, scanner);
                            } else if (QuestionInteractive.yesOrNoQuestion("Вы хотите удалить национальность автора?", scanner)) {
                                author.setNationality(null);
                            }
                        }
                    }

                    case 6 -> {
                        UpdateInteractiveLocation.updateLocation(author.getLocation(), scanner);
                    }

                    default -> {
                        System.out.printf("Введите число от 1 до %d", pointsOfPerson.size());
                        System.out.println();
                    }
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Введите число");
            }

        }

        return author;


    }

}
