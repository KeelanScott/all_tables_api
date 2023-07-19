package org.kainos.ea.cli;

public class Band {
    private int id;
    private String name;
    private int level;

    public Band(int id, String name, int level) {
        this.setId(id);
        this.setName(name);
        this.setLevel(level);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
