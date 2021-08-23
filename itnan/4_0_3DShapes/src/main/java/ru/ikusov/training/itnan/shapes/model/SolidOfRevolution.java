package ru.ikusov.training.itnan.shapes.model;

public abstract class SolidOfRevolution implements Shape {
    protected static final double PEE = Math.PI;

    private final double radius;

    public SolidOfRevolution(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}
