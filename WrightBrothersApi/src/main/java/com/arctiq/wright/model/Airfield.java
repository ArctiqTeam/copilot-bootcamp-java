package com.arctiq.wright.model;

public class Airfield {
    private int id;
    private String name;
    private String location;
    private String datesOfUse;
    private String significance;

    public Airfield(int id, String name, String location, String datesOfUse, String significance) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.datesOfUse = datesOfUse;
        this.significance = significance;
    }

    // getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDatesOfUse() {
        return datesOfUse;
    }

    public String getSignificance() {
        return significance;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDatesOfUse(String datesOfUse) {
        this.datesOfUse = datesOfUse;
    }

    public void setSignificance(String significance) {
        this.significance = significance;
    }
}