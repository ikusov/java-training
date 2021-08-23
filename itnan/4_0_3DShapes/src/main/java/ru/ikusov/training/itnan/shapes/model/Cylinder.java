package ru.ikusov.training.itnan.shapes.model;

import static ru.ikusov.training.utils.MyMath.vkvadrate;

public class Cylinder extends SolidOfRevolution {
    private final double height;

    public Cylinder(double r, double h) {
        super(r);
        this.height = h;
    }

    @Override
    public double getVolume() {
        double r = getRadius();
        return PEE * vkvadrate(r) * height;
    }
}
