package org.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BandWithDetailsResponse {
    private Band band;
    private List<BandCompetency> bandCompetencies;
    private List<Integer> trainingCourseIds;

    public BandWithDetailsResponse(@JsonProperty("band") Band band,
                                   @JsonProperty("bandCompetencies") List<BandCompetency> bandCompetencies,
                                   @JsonProperty("trainingCourseIds") List<Integer> trainingCourseIds) {
        this.band = band;
        this.bandCompetencies = bandCompetencies;
        this.trainingCourseIds = trainingCourseIds;
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

    public List<Integer> getTrainingCourseIds() {
        return trainingCourseIds;
    }

    public void setTrainingCourseIds(List<Integer> trainingCourses) {
        this.trainingCourseIds = trainingCourses;
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
                trainingCourseIds.equals(bandWithDetailsResponse.trainingCourseIds);
    }
}
