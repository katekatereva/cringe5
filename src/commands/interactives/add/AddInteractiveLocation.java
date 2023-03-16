package commands.interactives.add;

import models.Location;

import java.util.Scanner;

public class AddInteractiveLocation {


    public static void interactiveLocation(Location location, Scanner scanner) {
        interactiveXofLocation(location, scanner);
        interactiveYofLocation(location, scanner);
        interactiveZofLocation(location, scanner);
    }

    public static void interactiveXofLocation(Location location, Scanner scanner) {

        while (true) {
            try {
                System.out.print("Введите координату X: ");
                location.setX(Long.parseLong(scanner.nextLine()));
                break;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Координата X должна быть числом");
            }
        }

    }

    public static void interactiveYofLocation(Location location, Scanner scanner) {

        while (true) {
            try {
                System.out.print("Введите координату Y: ");
                location.setY(Long.parseLong(scanner.nextLine()));
                break;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Координата Y должна быть числом");
            }
        }
    }

    public static void interactiveZofLocation(Location location, Scanner scanner) {

        while (true) {
            try {
                System.out.print("Введите координату Z: ");
                location.setZ(Long.parseLong(scanner.nextLine()));
                break;
            } catch (NumberFormatException numberFormatException) {
                System.out.println("Координата Z должна быть числом");
            }
        }
    }

}