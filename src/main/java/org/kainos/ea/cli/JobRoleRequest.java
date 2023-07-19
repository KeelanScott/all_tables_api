package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRoleRequest {


    private String name;
    private int bandId;
    private String specification;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBandId() {
        return bandId;
    }

    public void setBandId(int bandId) {
        this.bandId = bandId;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    @JsonCreator
    public JobRoleRequest(
            @JsonProperty("name") String name,
            @JsonProperty("bandId") int bandId,
            @JsonProperty("specification") String specification) {
            this.name = name;
            this.bandId = bandId;
            setSpecification(specification);
    }
}
