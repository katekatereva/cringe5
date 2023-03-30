package ru.itmo.models;


public enum Country {
    RUSSIA("RUSSIA"),
    SPAIN("SPAIN"),
    ITALY("ITALY"),
    THAILAND("THAILAND"),
    JAPAN("JAPAN");


    private final String title;

    Country(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static Country isCountry(String title) {
        for (Country country : Country.values()) {
            if (country.getTitle().equalsIgnoreCase(title)) {
                return country;
            }
        }
        return null;
    }

}
