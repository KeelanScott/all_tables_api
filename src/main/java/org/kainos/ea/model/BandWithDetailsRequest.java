package org.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BandWithDetailsRequest {
    private BandRequest band;
    private BandCompetencyRequest[] bandCompetencies;
    private int[] trainingCourses;

    @JsonCreator
    public BandWithDetailsRequest(@JsonProperty("band") BandRequest band,
                                  @JsonProperty("bandCompetencies") BandCompetencyRequest[] bandCompetencies,
                                  @JsonProperty("trainingCourses") int[] trainingCourses) {
        this.band = band;
        this.bandCompetencies = bandCompetencies;
        this.trainingCourses = trainingCourses;
    }

    public BandRequest getBand() {
        return band;
    }

    public void setBand(BandRequest band) {
        this.band = band;
    }

    public BandCompetencyRequest[] getBandCompetencies() {
        return bandCompetencies;
    }

    public void setBandCompetencies(BandCompetencyRequest[] bandCompetencies) {
        this.bandCompetencies = bandCompetencies;
    }

    public int[] getTrainingCourses() {
        return trainingCourses;
    }

    public void setTrainingCourses(int[] trainingCourses) {
        this.trainingCourses = trainingCourses;
    }
}
