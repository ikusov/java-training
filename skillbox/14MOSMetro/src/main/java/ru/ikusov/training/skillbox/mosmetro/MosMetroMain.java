package ru.ikusov.training.skillbox.mosmetro;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import ru.ikusov.training.utils.Console;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.ikusov.training.skillbox.mosmetro.MosMetroJSON.printFromFile;
import static ru.ikusov.training.skillbox.mosmetro.MosMetroJSON.save;
import static ru.ikusov.training.utils.Console.p;

public class MosMetroMain {
    public static final String fileName = "src/main/resources/Moskau_Metro.json";

    public static final void main(String... kljsdf) throws IOException, ParseException {
        MosMetroParser.parseWebSite();
        //print();
        save(fileName);
        //test();
        printFromFile(fileName);

        p("\n\nConnections quantity: " + MosMetroParser.connections.size());
    }

    public static final void print() {
        MosMetroParser.lineList.forEach(line -> {
            p(line);
            line.getStations().forEach(Console::p);
        });
        MosMetroParser.connections.forEach(connection -> {
            p("\nConnection:");
            connection.forEach(station -> {
                p(String.format("\tLine: %s, Station: %s", station.getLine().getNumber(), station.getName()));
            });
        });
    }



    public static final void test() throws IOException {
        JSONObject line1 = new JSONObject(),
                line2 = new JSONObject();
        line1.put("number", "2");
        line1.put("name", "Замоскворецкая линия");

        line2.put("number", "D1");
        line2.put("name", "МЦД-2");

        JSONArray lines = new JSONArray();
        lines.add(line1);
        lines.add(line2);

        JSONArray line1Stations = new JSONArray(),
                    line2Stations = new JSONArray();
        line1Stations.add("Ховрино");
        line1Stations.add("Беломорская");
        line1Stations.add("Речной вокзал");

        line2Stations.add("Одинцово");
        line2Stations.add("Баковка");

        JSONObject stations = new JSONObject();
        stations.put("2", line1Stations);
        stations.put("D1", line2Stations);

        JSONObject mM = new JSONObject();
        mM.put("lines", lines);
        mM.put("stations", stations);

        p(JSONValue.toJSONString(mM));
        Files.writeString(Paths.get("test.json"), JSONValue.toJSONString(mM));
    }
}
