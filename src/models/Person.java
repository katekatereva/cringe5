package models;


import models.eye.Color;

import java.time.LocalDate;

public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.LocalDate birthday; //Поле может быть null
    private models.eye.Color eyeColor; //Поле не может быть null
    private models.hair.Color hairColor; //Поле может быть null
    private Country nationality; //Поле может быть null
    private Location location; //Поле не может быть null

    public Person() {}
    public Person(Person person) {

        if (person != null) {
            this.birthday = person.birthday;

            this.eyeColor = person.eyeColor;

            this.hairColor = person.hairColor;

            this.name = person.name;

            this.nationality = person.nationality;

            this.location = new Location(person.location);
        }


    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public models.hair.Color getHairColor() {
        return hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public Location getLocation(){
        return new Location(location);
    }

    public boolean setName(String name) {
        if (name == null) {
            return false;
        }
        if (name.isBlank()) {
            return false;
        }

        this.name = name;
        return true;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public boolean setEyeColor(Color eyeColor) {
        if (eyeColor == null) {
            return false;
        }
        this.eyeColor = eyeColor;
        return true;
    }

    public boolean setHairColor(models.hair.Color hairColor) {
        this.hairColor = hairColor;
        return true;
    }

    public boolean setNationality(Country nationality) {
        this.nationality = nationality;
        return true;
    }

    public boolean setLocation(Location location){
        if (location == null) {
            return false;
        }
        this.location = new Location(location);
        return true;
    }

}