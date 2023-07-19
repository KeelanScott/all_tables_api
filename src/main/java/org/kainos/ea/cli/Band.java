package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Band {
    private int id;
    private String name;
    private int level;

    @JsonCreator
    public Band(
            @JsonProperty("id") int id,
            @JsonProperty("name") String name,
            @JsonProperty("level") int level) {
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
