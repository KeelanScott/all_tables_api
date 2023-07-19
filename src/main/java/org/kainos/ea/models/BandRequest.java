package org.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BandRequest {
    private String name;
    private int levelId;
    private String responsibilities;

    @JsonCreator
    public BandRequest(@JsonProperty("name") String name,
                       @JsonProperty("levelId") int levelId,
                       @JsonProperty("responsibilities") String responsibilities) {
        this.name = name;
        this.levelId = levelId;
        this.responsibilities = responsibilities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }
}
