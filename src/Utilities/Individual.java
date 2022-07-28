package Utilities;

import java.util.ArrayList;

public class Individual {
    ArrayList<Boolean> objects;

    private Integer capacity = 0;
    private Integer score = 0;

    public Individual(ArrayList<Boolean> objects) {
        this.objects = objects;
    }

    public ArrayList<Boolean> getObjects() {
        return objects;
    }

    public Boolean getObjects(int index) {
        return objects.get(index);
    }

    public void setObjects(Integer index, Boolean newObject) {
        this.objects.set(index, newObject);
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
