package com.example.project2.ui.home;

public class Car {
    private String type,model,year;

    public Car() {
    }

    public Car(String type, String model, String year) {
        this.type = type;
        this.model = model;
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
