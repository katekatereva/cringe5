package managers.commandManager.utils;


public class GeneratorID {
    private static int id = 1;

    public static Integer newId(){
        return id++;
    }
}
