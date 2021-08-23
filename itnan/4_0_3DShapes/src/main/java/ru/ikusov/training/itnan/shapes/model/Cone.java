package ru.ikusov.training.itnan.shapes.model;

public class Cone extends ArbitrarySolidOfRevolution {
    private final double radius;

    public Cone(double radius, double height) {
        super(height, 100);
        this.radius = radius;
    }

    @Override
    public double getRadiusAt(double h) {
        h = h<0?0:Math.min(h, getHeight());
        return radius * h / getHeight();
    }
}
