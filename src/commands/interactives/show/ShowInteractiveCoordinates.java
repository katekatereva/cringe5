package commands.interactives.show;

import commands.interactives.utills.ShowSpacesCharacters;
import models.Coordinates;

public class ShowInteractiveCoordinates {

    public static void coordinatesShow(Coordinates coordinates, int levelSpace) {


        ShowSpacesCharacters.showSpace(levelSpace);
        System.out.printf("Координата X: %.2f", coordinates.getX());
        System.out.println();

        ShowSpacesCharacters.showSpace(levelSpace);
        if (coordinates.getY() == null) {
            System.out.println("Координата Y: отсутствует");
        } else {
            System.out.printf("Координата Y: %d", coordinates.getY());
        }
        System.out.println();

    }

}