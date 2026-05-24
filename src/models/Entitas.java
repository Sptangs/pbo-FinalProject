package models;

public abstract class Entitas {

    private int id;
    private static int counter = 1;

    public Entitas() {
        this.id = counter++;
    }

    public Entitas(int id, boolean fromFile) {
        this.id = id;
        if (id >= counter) counter = id + 1;
    }

    public int getId() {
        return id;
    }
}