package org.kainos.ea.model;

public class TrainingCourse {

    private int id;
    private String name;
    private String description;

    public TrainingCourse(int id, String name, String description) {
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
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
