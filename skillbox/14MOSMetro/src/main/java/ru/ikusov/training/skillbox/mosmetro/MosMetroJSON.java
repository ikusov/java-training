package ru.ikusov.training.skillbox.mosmetro;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ru.ikusov.training.skillbox.mosmetro.MosMetroParser.connections;
import static ru.ikusov.training.skillbox.mosmetro.MosMetroParser.lineList;
import static ru.ikusov.training.utils.Console.p;

public class MosMetroJSON {

    public static void save(String fileName) throws IOException {

        JSONObject jStations = new JSONObject();
        JSONArray jLines = new JSONArray();
        lineList.stream().forEach(line -> {
            JSONArray jLineStations = new JSONArray();
            line.getStations().forEach(station -> jLineStations.add(station.getName()));
            jStations.put(line.getNumber(), jLineStations);

            JSONObject jLine = new JSONObject();
            jLine.put("number", line.getNumber());
            jLine.put("name", line.getName());
            jLines.add(jLine);
        });

        JSONArray jConnections = new JSONArray();
        connections.forEach(connection -> {
            JSONArray jConnection = new JSONArray();
            connection.forEach(station -> {
                JSONObject jStation = new JSONObject();
                jStation.put("line", station.getLine().getNumber());
                jStation.put("station", station.getName());
                jConnection.add(jStation);
            });
            jConnections.add(jConnection);
        });

        JSONObject moskauMetro = new JSONObject();
        moskauMetro.put("stations", jStations);
        moskauMetro.put("connections", jConnections);
        moskauMetro.put("lines", jLines);

        Files.writeString(Paths.get(fileName), JSONValue.toJSONString(moskauMetro));
    }

    public static void printFromFile(String fileName) throws IOException, ParseException {

        JSONParser parser = new JSONParser();
        JSONObject jMoskauMetro = (JSONObject) parser.parse(getStringFromFile(fileName));

        JSONObject jStations = (JSONObject)jMoskauMetro.get("stations");
        Set<String> jLines = jStations.keySet();

        for (String jLine : jLines) {
            JSONArray jLineStations = ((JSONArray)jStations.get(jLine));
            p(String.format("Line number: %s, stations quantity: %d", jLine, jLineStations.size()));
            jLineStations.forEach(jStation -> p("\t" + jStation));
        }

    }

    private static String getStringFromFile(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        Files.readAllLines(Paths.get(fileName)).forEach(line -> sb.append(line));
        return sb.toString();
    }
}
