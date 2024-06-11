package com.arctiq.wright.model;

import java.util.Objects;

public class Plane {
    private int id;
    private String name;
    private int year;
    private String description;
    private int rangeInKm;

    public Plane(int id, String name, int year, String description, int rangeInKm) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.description = description;
        this.rangeInKm = rangeInKm;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRangeInKm() {
        return rangeInKm;
    }

    public void setRangeInKm(int rangeInKm) {
        this.rangeInKm = rangeInKm;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Plane plane = (Plane) obj;
        return id == plane.id &&
               year == plane.year &&
               rangeInKm == plane.rangeInKm &&
               Objects.equals(name, plane.name) &&
               Objects.equals(description, plane.description);
    }
}
