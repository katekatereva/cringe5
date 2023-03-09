package models;

public class Location {
    private Long x; //Поле не может быть null
    private long y;
    private Long z; //Поле не может быть null

    public void setX(Long x) {
        this.x = x;
    }

    public void setY(long y) {
        this.y = y;
    }

    public void setZ(Long z) {
        this.z = z;
    }

    public Long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public Long getZ() {
        return z;
    }

    @Override
    public Location clone() throws CloneNotSupportedException {
        Location location = new Location();
        location.setX(this.getX());
        location.setY(this.getY());
        return location;
    }
}