package commands.interactives.update;

import commands.interactives.add.AddInteractiveLocation;
import commands.interactives.show.ShowInteractiveLocation;
import models.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UpdateInteractiveLocation {

    public static void updateLocation(Location location, Scanner scanner) {
        List<String> pointsOfLocation = new ArrayList<>();

        pointsOfLocation.add("Координата X");
        pointsOfLocation.add("Координата Y");
        pointsOfLocation.add("Координата Z");

        boolean isUpdate = true;

        while (isUpdate) {
            try {

                System.out.println("---------------------------------------------------------------------------");
                ShowInteractiveLocation.showLocation(location, 0);
                System.out.println("---------------------------------------------------------------------------");

                for (int i = 0; i < pointsOfLocation.size(); i++) {
                    System.out.printf("%d. %s", i + 1, pointsOfLocation.get(i));
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
                        AddInteractiveLocation.interactiveXofLocation(location, scanner);
                    }
                    case 2 -> {
                        AddInteractiveLocation.interactiveYofLocation(location, scanner);
                    }
                    case 3 -> {
                        AddInteractiveLocation.interactiveZofLocation(location, scanner);
                    }
                    default -> {
                        System.out.printf("Введите число от 1 до %d", pointsOfLocation.size());
                        System.out.println();
                    }
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Введите число");
            }
        }

    }

}