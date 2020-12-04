package com.mechanitis.demo.spock;

public class ShapeFactory {
    private final Renderer renderer;

    public ShapeFactory(Renderer renderer) {
        this.renderer = renderer;
    }

    public Polygon createDefaultPolygon() {
        return new Polygon(4, renderer);
    }
}
