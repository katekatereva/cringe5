package commands.interactives.add;

import models.Difficulty;
import models.LabWork;

import java.util.Scanner;

public class AddInteractiveLabWork {

    public static void interactiveName(LabWork labWork, Scanner scanner) {
        System.out.print("Введите название работы: ");
        while (!labWork.setName(scanner.nextLine())) {
            System.out.println("Название работы должно быть не пустым");
            System.out.print("Введите название работы: ");
        }
    }

    public static void interactiveMinimalPoint(LabWork labWork, Scanner scanner) {
        while (true) {
            try {
                System.out.print("Введите значение минимальной точки: ");
                while (!labWork.setMinimalPoint(Integer.parseInt(scanner.nextLine()))) {
                    System.out.println("Значение минимальной точки должно быть больше нуля");
                    System.out.print("Введите значение минимальной точки: ");
                }
                break;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Значение минимальной точки должно быть числом");
            }
        }

    }

    public static void interactiveMaximumPoint(LabWork labWork, Scanner scanner) {
        while (true) {
            try {
                System.out.print("Введите значение максимальной точки: ");
                while (!labWork.setMaximumPoint(Integer.parseInt(scanner.nextLine()))) {
                    System.out.println("Значение максимальной точки должно быть больше нуля");
                    System.out.print("Введите значение максимальной точки: ");
                }
                break;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Значение максимальной точки должно быть числом");
            }
        }

    }


    public static void interactiveDifficulty(LabWork labWork, Scanner scanner) {

        Difficulty[] difficulties = Difficulty.values();

        for (int i = 0; i < difficulties.length; i++) {
            System.out.printf("%d. %s", i + 1, difficulties[i]);
            System.out.println();
        }
        while (true) {

            try {
                System.out.printf("Выберите номер от 1 до %d, чтобы выбрать сложность: ", difficulties.length);
                int positionOfDifficulty = Integer.parseInt(scanner.nextLine());
                if (positionOfDifficulty >= 1 && positionOfDifficulty <= difficulties.length) {
                    labWork.setDifficulty(difficulties[positionOfDifficulty - 1]);
                    break;
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Введите число");
            }

        }

    }


}
