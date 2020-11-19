package com.mechanitis.demo.spock;

class Shape {
    public final int numberOfSides;
    private Renderer renderer;

    Shape(int numberOfSides) {
        this.numberOfSides = numberOfSides;
    }

    public Shape(int numberOfSides, Renderer renderer) {
        this.numberOfSides = numberOfSides;
        this.renderer = renderer;
    }

    public int numberOfSides() {
        return numberOfSides;
    }

    public void draw() {
        for (int i = 0; i < numberOfSides; i++) {
            renderer.drawLine();
        }
    }
}
