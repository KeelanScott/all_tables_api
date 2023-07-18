package org.kainos.ea.cli;

public class JobRole {


    private int id;
    private String name;
    private int bandId;

    public JobRole(int id, String name, int bandId) {
        this.setId(id);
        this.setName(name);
        this.setBandId(bandId);
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
}
