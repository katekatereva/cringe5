package models;

public class Coordinates {
    private float x; //Максимальное значение поля: 863
    private Long y; //Значение поля должно быть больше -733, Поле не может быть null

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }
}