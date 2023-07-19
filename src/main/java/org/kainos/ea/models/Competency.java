package org.kainos.ea.models;

public class Competency {
    private int competencyId;
    private String name;

    public Competency(int competencyId, String name) {
        this.competencyId = competencyId;
        this.name = name;
    }

    public int getCompetencyId() {
        return competencyId;
    }

    public void setCompetencyId(int competencyId) {
        this.competencyId = competencyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
