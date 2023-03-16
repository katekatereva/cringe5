package commands.interactives.add;

import models.Person;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class AddInteractiveBirthDay {

    public static void interactiveBirthDay(Person author, Scanner scanner) {
        while (true) {

            try {
                int day = interactiveDayOfBirthDay(scanner);
                int month = interactiveMonthOfBirthDay(scanner);
                int year = interactiveYearOfBirthDay(scanner);
                author.setBirthday(LocalDate.of(year, month, day));
                break;
            } catch (DateTimeException dateTimeException) {
                System.out.println("Такой даты не существует");
            }

        }

    }
    public static int interactiveDayOfBirthDay(Scanner scanner) {
        while (true) {
            int day;
            try {
                System.out.print("Введите день: ");
                day = Integer.parseInt(scanner.nextLine());

                if (day >= 1 && day <= 31) {
                    return day;
                } else {
                    System.out.println("День должен быть в диапазоне 1 - 31");
                }

            } catch (NumberFormatException numberFormatException) {
                System.out.println("День должен быть числом");
            }

        }
    }

    public static int interactiveMonthOfBirthDay(Scanner scanner) {
        while (true) {

            try {
                System.out.print("Введите месяц: ");
                int month = Integer.parseInt(scanner.nextLine());

                if (month >= 1 && month <= 12) {
                    return month;
                } else {
                    System.out.println("Месяц должен быть в диапазоне 1 - 12");
                }

            } catch (NumberFormatException numberFormatException) {
                System.out.println("Месяц должен быть числом");
            }

        }
    }

    public static int interactiveYearOfBirthDay(Scanner scanner) {
        while (true) {

            try {
                System.out.print("Введите год: ");
                int year = Integer.parseInt(scanner.nextLine());

                if (year >= 1) {
                    return year;
                } else {
                    System.out.println("Год должен быть больше нуля");
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Год должен быть числом");
            }

        }
    }

}