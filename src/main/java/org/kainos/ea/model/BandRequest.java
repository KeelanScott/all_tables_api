package org.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BandRequest {
    private String name;
    private String level;
    private String responsibilities;

    @JsonCreator
    public BandRequest(@JsonProperty("name") String name,
                       @JsonProperty("level") String level,
                       @JsonProperty("responsibilities") String responsibilities) {
        this.name = name;
        this.level = level;
        this.responsibilities = responsibilities;
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

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }
}
