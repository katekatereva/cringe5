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

    public Location getLocation() throws CloneNotSupportedException {
        return location.clone();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setHairColor(models.hair.Color hairColor) {
        this.hairColor = hairColor;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public void setLocation(Location location) throws CloneNotSupportedException {
        this.location = location.clone();
    }

    @Override
    protected Person clone() throws CloneNotSupportedException {
        Person person = new Person();

        person.setBirthday(this.getBirthday());
        person.setEyeColor(this.getEyeColor());
        person.setLocation(this.getLocation());
        person.setName(this.getName());
        person.setNationality(this.getNationality());
        person.setHairColor(this.getHairColor());

        return person;
    }
}