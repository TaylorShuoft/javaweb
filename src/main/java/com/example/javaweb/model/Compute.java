package com.example.javaweb.model;


public class Compute {
    private double upperBase;
    private double lowerBase;
    private double height;
    private double area;

    public Compute() {
    }

    public double getUpperBase() {
        return upperBase;
    }

    public void setUpperBase(double upperBase) {
        this.upperBase = upperBase;
    }

    public double getLowerBase() {
        return lowerBase;
    }

    public void setLowerBase(double lowerBase) {
        this.lowerBase = lowerBase;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public void calculateArea() {
        this.area = (this.upperBase + this.lowerBase) * this.height / 2;
    }
}