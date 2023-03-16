package commands.interactives.update;

import commands.interactives.add.AddInteractiveLabWork;
import commands.interactives.add.AddInteractivePerson;
import commands.interactives.show.ShowInteractiveLabWork;
import commands.interactives.utills.QuestionInteractive;
import models.LabWork;
import models.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UpdateInteractiveLabWork {

    public static void updateLabWork(LabWork labWork, Scanner scanner) {

        List<String> pointsOfLabWork = new ArrayList<>();

        pointsOfLabWork.add("Название лабораторной работы");
        pointsOfLabWork.add("Информация о координатах");
        pointsOfLabWork.add("Значение минимальной точки");
        pointsOfLabWork.add("Значение максимальной точки");
        pointsOfLabWork.add("Сложность лабораторной работы");
        pointsOfLabWork.add("Информация об авторе");



        boolean isUpdate = true;
        while (isUpdate) {
            try {

                System.out.println("---------------------------------------------------------------------------");
                ShowInteractiveLabWork.showLabWork(labWork, 0);
                System.out.println("---------------------------------------------------------------------------");

                for (int i = 0; i < pointsOfLabWork.size(); i++) {
                    System.out.printf("%d. %s", i + 1, pointsOfLabWork.get(i));
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
                        AddInteractiveLabWork.interactiveName(labWork, scanner);
                    }
                    case 2 -> {
                        labWork.setCoordinates(UpdateInteractiveCoordinates.updateCoordinates(labWork.getCoordinates(), scanner));
                    }
                    case 3 -> {
                        AddInteractiveLabWork.interactiveMinimalPoint(labWork, scanner);
                    }
                    case 4 -> {
                        AddInteractiveLabWork.interactiveMaximumPoint(labWork, scanner);
                    }
                    case 5 -> {
                        if (labWork.getDifficulty() == null) {
                            if (QuestionInteractive.yesOrNoQuestion("Вы хотите добавить сложность работе?", scanner)) {
                                AddInteractiveLabWork.interactiveDifficulty(labWork, scanner);
                            }
                        }
                        else {
                            if (QuestionInteractive.yesOrNoQuestion("Вы хотите изменить сложность работы?", scanner)) {
                                AddInteractiveLabWork.interactiveDifficulty(labWork, scanner);
                            } else if (QuestionInteractive.yesOrNoQuestion("Вы хотите удалить сложность работе?", scanner)) {
                                labWork.setDifficulty(null);
                            }
                        }

                    }
                    case 6 -> {
                        if (labWork.getAuthor() == null) {
                            if (QuestionInteractive.yesOrNoQuestion("Вы хотите добавить автора работы?", scanner)) {
                                Person author = new Person();
                                AddInteractivePerson.interactivePerson(author, scanner);
                                labWork.setAuthor(author);
                            }
                        }
                        else {
                            if (QuestionInteractive.yesOrNoQuestion("Вы хотите изменить автора работы?", scanner)) {
                                Person author = UpdateInteractivePerson.updatePerson(labWork.getAuthor(), scanner);
                                labWork.setAuthor(author);
                            } else if (QuestionInteractive.yesOrNoQuestion("Вы хотите удалить автора работы?", scanner)) {
                                labWork.setAuthor(null);
                            }
                        }
                    }
                    default -> {
                        System.out.printf("Введите число от 1 до %d", pointsOfLabWork.size());
                        System.out.println();
                    }
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Введите число");
            }
        }



    }

}
