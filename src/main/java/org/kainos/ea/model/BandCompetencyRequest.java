package org.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BandCompetencyRequest {
    private int competencyID;
    private String description;

    public BandCompetencyRequest(@JsonProperty("competencyId") int competencyID,
                                 @JsonProperty("description") String description) {
        this.competencyID = competencyID;
        this.description = description;
    }

    public int getCompetencyID() {
        return competencyID;
    }

    public void setCompetencyID(int competencyID) {
        this.competencyID = competencyID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
