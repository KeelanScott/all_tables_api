package org.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BandWithDetailsResponse {
    private Band band;
    private List<BandCompetency> bandCompetencies;
    private List<Integer> trainingCourses;

    public BandWithDetailsResponse(@JsonProperty("band") Band band,
                                   @JsonProperty("bandCompetencies") List<BandCompetency> bandCompetencies,
                                   @JsonProperty("trainingCourseIds") List<Integer> trainingCourses) {
        this.band = band;
        this.bandCompetencies = bandCompetencies;
        this.trainingCourses = trainingCourses;
    }

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public List<BandCompetency> getBandCompetencies() {
        return bandCompetencies;
    }

    public void setBandCompetencies(List<BandCompetency> bandCompetencies) {
        this.bandCompetencies = bandCompetencies;
    }

    public List<Integer> getTrainingCourses() {
        return trainingCourses;
    }

    public void setTrainingCourses(List<Integer> trainingCourses) {
        this.trainingCourses = trainingCourses;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof BandWithDetailsResponse)) {
            return false;
        }
        BandWithDetailsResponse bandWithDetailsResponse = (BandWithDetailsResponse) o;
        return band.equals(bandWithDetailsResponse.band) &&
                bandCompetencies.equals(bandWithDetailsResponse.bandCompetencies) &&
                trainingCourses.equals(bandWithDetailsResponse.trainingCourses);
    }
}
