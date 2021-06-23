package ru.ikusov.training.skillbox.mosmetro;

public class Station {
    private String name;
    private Line line;

    Station(String name, Line line) {
        this.name = name;
        this.line = line;
    }

    public String toString() {
        return String.format("\t+%d) %s", line.getStations().indexOf(this)+1, name);
    }

    public String getName() {
        return name;
    }

    public Line getLine() {
        return line;
    }
}
