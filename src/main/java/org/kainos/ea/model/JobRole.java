package org.kainos.ea.model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRole {
    private int id;
    private String name;
    private int bandId;
    private Band band;
    private Capability capability;
    private String specification;

    @JsonCreator
    public JobRole(@JsonProperty("id") int id,
                   @JsonProperty("name") String name,
                   @JsonProperty("band") Band band,
                   @JsonProperty("Capability") Capability capability,
                   @JsonProperty("specification") String specification) {
        setId(id);
        setName(name);
        setBand(band);
        setCapability(capability);
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

    public Band getBand() {
        return band;
    }

    public void setBand(Band band) {
        this.band = band;
    }

    public Capability getCapability() {
        return capability;
    }

    public void setCapability(Capability capability) {
        this.capability = capability;
    }
}
