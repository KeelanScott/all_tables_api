package org.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Capability {
    private int id;
    private String name;
    private String description;

    @JsonCreator
    public Capability(@JsonProperty("id") int id,
                      @JsonProperty("name") String name,
                      @JsonProperty("description") String description) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
    }

    public Capability(int id, String name) {
        this.setId(id);
        this.setName(name);
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

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}