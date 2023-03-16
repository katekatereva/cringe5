package commands.interactives.update;

import commands.interactives.add.AddInteractiveCoordinate;
import commands.interactives.show.ShowInteractiveCoordinates;
import models.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UpdateInteractiveCoordinates {

    public static Coordinates updateCoordinates(Coordinates coordinates, Scanner scanner) {

        List<String> pointsOfCoordinates = new ArrayList<>();

        pointsOfCoordinates.add("Координата X");
        pointsOfCoordinates.add("Координата Y");



        boolean isUpdate = true;

        while (isUpdate) {
            try {

                System.out.println("---------------------------------------------------------------------------");
                ShowInteractiveCoordinates.coordinatesShow(coordinates, 0);
                System.out.println("---------------------------------------------------------------------------");

                for (int i = 0; i < pointsOfCoordinates.size(); i++) {
                    System.out.printf("%d. %s", i + 1, pointsOfCoordinates.get(i));
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
                        AddInteractiveCoordinate.interactiveX(coordinates, scanner);
                    }
                    case 2 -> {
                        AddInteractiveCoordinate.interactiveY(coordinates, scanner);
                    }
                    default -> {
                        System.out.printf("Введите число от 1 до %d", pointsOfCoordinates.size());
                        System.out.println();
                    }
                }
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Введите число");
            }
        }

        return coordinates;


    }

}