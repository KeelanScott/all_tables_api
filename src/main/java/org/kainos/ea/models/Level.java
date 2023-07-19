package org.kainos.ea.models;

public class Level {
    private int levelId;
    private String name;

    public Level(int levelId, String name) {
        this.levelId = levelId;
        this.name = name;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
