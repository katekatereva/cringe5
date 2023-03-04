package models.utils;

/**
 * Генератор id
 */

public class GeneratorID {
    private static int id = 1;

    public static Integer newId(){
        return id++;
    }
}
