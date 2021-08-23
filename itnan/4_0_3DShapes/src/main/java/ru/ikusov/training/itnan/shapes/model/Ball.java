package ru.ikusov.training.itnan.shapes.model;

import static ru.ikusov.training.utils.MyMath.vkube;

public class Ball extends SolidOfRevolution {
    public Ball(double r) {
        super(r);
    }

    @Override
    public double getVolume() {
        double r = getRadius();
        return PEE * vkube(r) * 4 / 3;
    }
}
