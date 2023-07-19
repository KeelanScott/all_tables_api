package org.kainos.ea.models;

public class TrainingCourse {
    private String name;
    private String description;

    public TrainingCourse(String name, String description) {
        this.setName(name);
        this.setDescription(description);
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
