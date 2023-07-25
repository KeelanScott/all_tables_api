package org.kainos.ea.model;

public class JobRole {


    private int id;
    private String name;
    private int bandId;
    private Band band;
    private Capability capability;

    private String specification;

    public JobRole(int id, String name, Band band, Capability capability) {
        this.setId(id);
        this.setName(name);
        this.setBand(band);
        this.setCapability(capability);
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