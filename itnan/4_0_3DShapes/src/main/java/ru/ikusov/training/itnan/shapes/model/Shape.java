package ru.ikusov.training.itnan.shapes.model;

public interface Shape extends Comparable<Shape> {
    double getVolume();

    @Override
    default int compareTo(Shape o) {
        return Double.compare(this.getVolume(), o.getVolume());
    }
}
