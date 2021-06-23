package ru.ikusov.training.skillbox.mosmetro;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private String number;
    private String name;
    private List<Station> stations;

    Line(String number, String name) {
        this.number = number;
        this.name = name;
        this.stations = new ArrayList<>();
    }


    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public String toString() {
        return String.format("\n%s. %s", number, name);
    }

    public Station getStationByName(String name) {
        for (Station station : stations) if (station.getName().equals(name)) return station;
        return null;
    }
}
