package models;

import java.time.LocalDate;

public class LabWork {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int minimalPoint; //Значение поля должно быть больше 0
    private Integer maximumPoint; //Поле не может быть null, Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле может быть null
    private Person author; //Поле может быть null

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() throws CloneNotSupportedException {
        return coordinates.clone();
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public int getMinimalPoint() {
        return minimalPoint;
    }

    public Integer getMaximumPoint() {
        return maximumPoint;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Person getAuthor() throws CloneNotSupportedException {
        return author.clone();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) throws CloneNotSupportedException {
        this.coordinates = coordinates.clone();
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setMinimalPoint(int minimalPoint) {
        this.minimalPoint = minimalPoint;
    }

    public void setMaximumPoint(Integer maximumPoint) {
        this.maximumPoint = maximumPoint;
    }
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setAuthor(Person author) throws CloneNotSupportedException {
        this.author = author.clone();
    }


    public void cloneShallow(LabWork labWorkCopy) throws CloneNotSupportedException{

        this.setAuthor(labWorkCopy.getAuthor());
        this.setCoordinates(labWorkCopy.getCoordinates());
        this.setDifficulty(labWorkCopy.getDifficulty());
        this.setMinimalPoint(labWorkCopy.getMinimalPoint());
        this.setMaximumPoint(labWorkCopy.getMaximumPoint());
        this.setName(labWorkCopy.getName());
    }

    @Override
    public LabWork clone() throws CloneNotSupportedException {
        LabWork labWork = new LabWork();

        labWork.setId(this.getId());
        labWork.setCreationDate(this.getCreationDate());

        labWork.setAuthor(this.getAuthor());
        labWork.setCoordinates(this.getCoordinates());
        labWork.setDifficulty(this.getDifficulty());
        labWork.setMinimalPoint(this.getMinimalPoint());
        labWork.setMaximumPoint(this.getMaximumPoint());
        labWork.setName(this.getName());

        return labWork;
    }
}