package commands.interactives.show;

import commands.interactives.utills.ShowSpacesCharacters;
import models.Location;

public class ShowInteractiveLocation {

    public static void showLocation(Location location, int levelSpace) {

        ShowSpacesCharacters.showSpace(levelSpace);
        if (location.getX() == null) {
            System.out.println("Координата X: отсутствует");
        } else {
            System.out.printf("Координата X: %d", location.getX());
            System.out.println();
        }

        ShowSpacesCharacters.showSpace(levelSpace);
        System.out.printf("Координата Y: %d", location.getY());
        System.out.println();

        ShowSpacesCharacters.showSpace(levelSpace);
        if (location.getZ() == null) {
            System.out.println("Координата Z: отсутствует");
        } else {
            System.out.printf("Координата Z: %d", location.getZ());
            System.out.println();
        }

    }

}