package ru.ikusov.training.itnan.shapes.service;

import ru.ikusov.training.itnan.shapes.model.*;

import static ru.ikusov.training.utils.MyMath.r;

public class ShapeFactory {
    private final static ShapeFactory theInstance = new ShapeFactory();

    private ShapeFactory(){}

    public static ShapeFactory getInstance() {
        return theInstance;
    }

    public Shape createRandomShape(double maxVolume) {
        double maxLinearSize = Math.pow(maxVolume, 1./3.);
        switch (r(4) + 1) {
            case 1:
                return new Pyramid(r(maxLinearSize), r(maxLinearSize));
            case 2:
                return new Cylinder(r(maxLinearSize), r(maxLinearSize));
            case 3:
                return new Cone(r(maxLinearSize), r(maxLinearSize));
            default:
                return new Ball(r(maxLinearSize));
        }
    }
}
