package com.example.javaweb.model;


public class Student {
    private double math;
    private double english;
    private double computer;
    private double total;

    public Student() {
    }

    public double getMath() {
        return math;
    }

    public void setMath(double math) {
        this.math = math;
    }

    public double getEnglish() {
        return english;
    }

    public void setEnglish(double english) {
        this.english = english;
    }

    public double getComputer() {
        return computer;
    }

    public void setComputer(double computer) {
        this.computer = computer;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void calculateTotal() {
        this.total = this.math + this.english + this.computer;
    }
}