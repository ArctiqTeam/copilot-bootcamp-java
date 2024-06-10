package com.arctiq.wright.model;

public class Plane {
    private int id;
    private String name;
    private int year;
    private String description;
    private int rangeInKm;

    // New property
    private String imageUrl;

    public Plane(int id, String name, int year, String description, int rangeInKm, String imageUrl) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.description = description;
        this.rangeInKm = rangeInKm;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() { return imageUrl;}

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
