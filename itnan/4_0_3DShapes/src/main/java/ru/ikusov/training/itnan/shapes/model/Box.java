package ru.ikusov.training.itnan.shapes.model;

public class Box implements Shape {
    private double contentVolume = 0;
    private final double volume;

    public Box(double volume) {
        this.volume = volume;
    }

    public boolean add(Shape shape) {
        double newVolume = contentVolume + shape.getVolume();
        double boxVolume = getVolume();
        if (newVolume <= boxVolume) {
            contentVolume = newVolume;
            return true;
        } else return false;
    }

    public double getFullness() {
        return contentVolume / getVolume();
    }

    @Override
    public double getVolume() {
        return volume;
    }
}
