package commands.interactives.update;

import commands.interactives.add.AddInteractiveBirthDay;
import commands.interactives.show.ShowInteractiveBirthDay;
import models.Person;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UpdateInteractiveBirthDay {

    public static void updateBirthDay(Person author, Scanner scanner) {

        List<String> pointsOfBirthDay = new ArrayList<>();

        LocalDate birthday = author.getBirthday();

        pointsOfBirthDay.add("День");
        pointsOfBirthDay.add("Месяц");
        pointsOfBirthDay.add("Год");



        boolean isUpdate = true;
        while (isUpdate) {

            int day = birthday.getDayOfMonth();
            int month = birthday.getMonth().getValue();
            int year = birthday.getYear();
            try {

                System.out.println("---------------------------------------------------------------------------");
                ShowInteractiveBirthDay.showDayOfBirth(birthday);
                System.out.println("---------------------------------------------------------------------------");

                for (int i = 0; i < pointsOfBirthDay.size(); i++) {
                    System.out.printf("%d. %s", i + 1, pointsOfBirthDay.get(i));
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
                        day = AddInteractiveBirthDay.interactiveDayOfBirthDay(scanner);
                        author.setBirthday(LocalDate.of(year, month, day));
                    }
                    case 2 -> {
                        month = AddInteractiveBirthDay.interactiveMonthOfBirthDay(scanner);
                        author.setBirthday(LocalDate.of(year, month, day));
                    }
                    case 3 -> {
                        year = AddInteractiveBirthDay.interactiveYearOfBirthDay(scanner);
                        author.setBirthday(LocalDate.of(year, month, day));
                    }
                    default -> {
                        System.out.printf("Введите число от 1 до %d", pointsOfBirthDay.size());
                        System.out.println();
                    }
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Введите число");
            } catch (DateTimeException dateTimeException) {
                System.out.println("Такой даты не существует");
            }



        }

    }

}