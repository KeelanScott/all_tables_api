package org.kainos.ea.models;

public class CompetencyElement {
    private int competencyElementId;
    private int competencyId;
    private String name;

    public CompetencyElement(int competencyElementId, int competencyId, String name) {
        this.competencyElementId = competencyElementId;
        this.competencyId = competencyId;
        this.name = name;
    }

    public int getCompetencyElementId() {
        return competencyElementId;
    }

    public void setCompetencyElementId(int competencyElementId) {
        this.competencyElementId = competencyElementId;
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
