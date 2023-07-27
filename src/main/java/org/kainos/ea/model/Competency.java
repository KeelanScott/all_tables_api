package org.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Competency {
    private int competencyId;
    private String name;

    @JsonCreator
    public Competency(@JsonProperty("competencyId") int competencyId,
                      @JsonProperty("name") String name) {
        this.competencyId = competencyId;
        this.name = name;
    }

    public int getCompetencyId() {
        return competencyId;
    }

    public void setCompetencyId(int competencyId) {
        this.competencyId = competencyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
