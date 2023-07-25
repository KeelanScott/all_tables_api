package org.kainos.ea.model;

public class Band {
    private int id;
    private String name;
    private String level;

    public Band(int id, String name, String level) {
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
