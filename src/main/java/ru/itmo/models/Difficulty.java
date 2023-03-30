package ru.itmo.models;


public enum Difficulty {
    VERY_HARD("VERY HARD"),
    INSANE("INSANE"),
    HOPELESS("HOPELESS");


    private final String title;

    Difficulty(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static Difficulty isDifficulty(String title) {
        for (Difficulty difficulty : Difficulty.values()) {
            if (difficulty.getTitle().equalsIgnoreCase(title)) {
                return difficulty;
            }
        }
        return null;
    }
}
