import core.Line;
import core.Station;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class RouteCalculatorTest {

    private static StationIndex stationIndex;
    private static RouteCalculator rc;

    @Before
    public void generateStationIndex () {
        stationIndex = new StationIndex();
        stationIndex.addLine(new Line(1, "Uno"));
        stationIndex.addLine(new Line(2, "Dos"));
        stationIndex.addLine(new Line(3, "Tres"));

        for (int i=1; i<4; i++) {
            for (int j=0; j<10; j++) {
                Line line = stationIndex.getLine(i);
                Station station = new Station(String.valueOf(i*10+j), line);

                stationIndex.addStation(station);
                line.addStation(station);
            }
        }

        List<Station> con1 = Stream.of("13", "23")
                            .map(stationIndex::getStation)
                            .collect(Collectors.toList());

        stationIndex.addConnection(Stream.of("13","23")
                .map(stationIndex::getStation)
                .collect(Collectors.toList()));

        stationIndex.addConnection(Stream.of("27","37")
                .map(stationIndex::getStation)
                .collect(Collectors.toList()));

        rc = new RouteCalculator(stationIndex);
    }

    @Test
    public void getShortestRouteWithTwoConnections() {
        List<Station> expected =
                Stream.of("10", "11", "12", "13", "23", "24", "25", "26", "27", "37", "36")
                .map(stationIndex::getStation)
                        .collect(Collectors.toList());

        List<Station> actual = rc.getShortestRoute(stationIndex.getStation("10"),
                                stationIndex.getStation("36"));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getShortestRouteWithOneConnection() {
        List<Station> expected =
                Stream.of("19", "18", "17", "16", "15", "14", "13", "23", "22", "21")
                .map(stationIndex::getStation)
                .collect(Collectors.toList());

        List<Station> actual = rc.getShortestRoute(stationIndex.getStation("19"),
                                                    stationIndex.getStation("21"));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getShortestRouteOnTheLine() {
        List<Station> expected =
                Stream.of("39", "38", "37", "36", "35", "34", "33")
                .map(stationIndex::getStation)
                .collect(Collectors.toList());

        List<Station> actual = rc.getShortestRoute(stationIndex.getStation("39"),
                                                    stationIndex.getStation("33"));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calculateDuration() {
        List<Station> route = rc.getShortestRoute(stationIndex.getStation("10"),
                stationIndex.getStation("36"));

        double expected = 27;
        double actual = rc.calculateDuration(route);

        Assert.assertEquals(expected, actual, 0.);
    }
}