package org.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BandTrainingCourse {
    private int bandId;
    private int trainingId;

    public BandTrainingCourse(@JsonProperty("bandId") int bandId,
                              @JsonProperty("trainingCourseId") int trainingId) {
        this.bandId = bandId;
        this.trainingId = trainingId;
    }

    public int getBandId() {
        return bandId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }
}
