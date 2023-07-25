package org.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Band {
    private int id;
    private String name;
    private String level;

    public Band() {

    }

    @JsonCreator
    public Band(
            @JsonProperty("id") int id,
            @JsonProperty("name") String name,
            @JsonProperty("level") String level) {
        this.setId(id);
        this.setName(name);
        this.setLevel(level);
    }

    public Band(String name, String level) {
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
