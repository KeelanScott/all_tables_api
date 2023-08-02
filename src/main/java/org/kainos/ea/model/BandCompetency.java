package org.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BandCompetency {
    private int bandID;
    private int competencyID;
    private String description;

    public BandCompetency(@JsonProperty("bandId") int bandID,
                          @JsonProperty("competencyId") int competencyID,
                          @JsonProperty("description") String description) {
        this.bandID = bandID;
        this.competencyID = competencyID;
        this.description = description;
    }

    public int getBandID() {
        return bandID;
    }

    public void setBandID(int bandID) {
        this.bandID = bandID;
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