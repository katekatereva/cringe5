package commands.interactives.add;

import models.Coordinates;

import java.util.Scanner;

public class AddInteractiveCoordinate {

    public static void interactiveX(Coordinates coordinates, Scanner scanner) {
        while (true) {
            try {
                System.out.print("Введите координату X: ");
                while (!coordinates.setX(Float.parseFloat(scanner.nextLine()))) {
                    System.out.printf("Координата X должна быть не больше %.2f", coordinates.getXMax());
                    System.out.println();
                    System.out.print("Введите координату X: ");
                }
                break;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Координата X должна быть числом");
            }
        }
    }

    public static void interactiveY(Coordinates coordinates, Scanner scanner) {

        while (true) {
            try {
                System.out.print("Введите координату Y: ");
                while (!coordinates.setY(Long.parseLong(scanner.nextLine()))) {
                    System.out.printf("Координата Y должна быть больше %d", coordinates.getYMin());
                    System.out.println();
                    System.out.print("Введите координату Y: ");
                }
                break;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Координата Y должна быть числом");
            }
        }
    }

}
