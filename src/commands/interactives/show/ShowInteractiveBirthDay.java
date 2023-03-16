package commands.interactives.show;

import java.time.LocalDate;

public class ShowInteractiveBirthDay {

    public static void showDayOfBirth(LocalDate birthday) {


        int day = birthday.getDayOfMonth();
        int month = birthday.getMonth().getValue();
        int year = birthday.getYear();

        System.out.printf("День: %d", day);
        System.out.printf("Месяц: %d", month);
        System.out.printf("Год: %d", year);


    }

}