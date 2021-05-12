package com.automobile.autodealer;

public class Auto {
    private int year;
    private String make;
    private String model;
    private String color;
    private String owner;
    private String vin;

    public Auto() {
    }
    public Auto(String vin) {
        this.vin = vin;
    }

    public Auto( int year, String make, String model, String vin) {
        this(year, make, model, null, null, vin);
    }

    public Auto(int year, String make, String model, String color, String owner, String vin) {
        this.year = year;
        this.make = make;
        this.model = model;
        this.color = color;
        this.owner = owner;
        this.vin = vin;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
