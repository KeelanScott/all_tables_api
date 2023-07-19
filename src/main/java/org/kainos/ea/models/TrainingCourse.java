package org.kainos.ea.models;

public class TrainingCourse {

    private int id;
    private String name;
    private String description;

    public TrainingCourse(int id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
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
