package org.kainos.ea.cli;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class JobRole {

    private int id;
    private String name;
    private int bandId;

    private String specification;

    public JobRole(int id, String name, int bandId) {
        this.setId(id);
        this.setName(name);
        this.setBandId(bandId);
    }

    public JobRole(int id, String name, int bandId, String specifictaion) {
        setId(id);
        setName(name);
        setBandId(bandId);
        setSpecification(specifictaion);
    }

    @JsonCreator
    public JobRole(@JsonProperty("name") String name,
                   @JsonProperty("band_id") int bandId,
                   @JsonProperty("specification")String specification) {
        setName(name);
        setBandId(bandId);
        setSpecification(specification);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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


}
