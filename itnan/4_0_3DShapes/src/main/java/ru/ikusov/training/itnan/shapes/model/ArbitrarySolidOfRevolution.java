package ru.ikusov.training.itnan.shapes.model;

import static ru.ikusov.training.utils.MyMath.vkvadrate;

public abstract class ArbitrarySolidOfRevolution implements Shape {
    private static final double PEE = Math.PI;
    private final double height;
    private final long discreteness;

    ArbitrarySolidOfRevolution(double height, long discreteness) {
        this.height = height;
        this.discreteness = discreteness;
    }

    protected abstract double getRadiusAt(double h);

    public double getHeight() {
        return height;
    }

    public double getVolume() {
        double dh = height/discreteness;
        double volume = 0;
        for (int i=0; i<discreteness; i++) {
            volume += (vkvadrate(getRadiusAt(i*dh))
                        + vkvadrate(getRadiusAt((i+1)*dh)))
                        * PEE
                        / 2;
        }
        return volume;
    }
}
