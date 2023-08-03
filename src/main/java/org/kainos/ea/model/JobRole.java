package org.kainos.ea.model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRole {
    private int id;
    private String name;
    private Band band;
    private Capability capability;
    private String specification;

    @JsonCreator
    public JobRole(@JsonProperty("id") int id,
                   @JsonProperty("name") String name,
                   @JsonProperty("band") Band band,
                   @JsonProperty("capability") Capability capability,
                   @JsonProperty("specification") String specification) {
        setId(id);
        setName(name);
        setBand(band);
        setCapability(capability);
        setSpecification(specification);
    }

    public JobRole(int id, String name, Band band, Capability capability) {
        setId(id);
        setName(name);
        setBand(band);
        setCapability(capability);
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
