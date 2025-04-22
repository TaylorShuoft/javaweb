package com.example.javaweb.model;


public class Triangle {
    private double side1;
    private double side2;
    private double side3;
    private boolean isTriangle;
    private double perimeter;

    public Triangle() {
    }

    public double getSide1() {
        return side1;
    }

    public void setSide1(double side1) {
        this.side1 = side1;
    }

    public double getSide2() {
        return side2;
    }

    public void setSide2(double side2) {
        this.side2 = side2;
    }

    public double getSide3() {
        return side3;
    }

    public void setSide3(double side3) {
        this.side3 = side3;
    }

    public boolean getIsTriangle() {
        return isTriangle;
    }

    public void setIsTriangle(boolean isTriangle) {
        this.isTriangle = isTriangle;
    }

    public double getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    public void checkTriangle() {
        this.isTriangle = (side1 + side2 > side3) && (side2 + side3 > side1) && (side1 + side3 > side2)
                && (side1 > 0) && (side2 > 0) && (side3 > 0);
        if (this.isTriangle) {
            this.perimeter = side1 + side2 + side3;
        } else {
            this.perimeter = 0;
        }
    }
}