package com.mechanitis.demo.spock;

class Shape {
    public final int numberOfSides;

    Shape(int numberOfSides) {
        this.numberOfSides = numberOfSides;
    }

    public int numberOfSides() {
        return numberOfSides;
    }

}
