package ru.ikusov.training.skillbox.mosmetro;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//moskau metro website singleton parser
public class MosMetroParser {
    private static final String mmURL = "https://www.moscowmap.ru/metro.html#lines";

    public static List<Line> lineList;
    public static Set<Set<Station>> connections;

    public static final void parseWebSite() throws IOException {
        Document document = Jsoup.connect(mmURL).maxBodySize(0).get();
        parseLinesAndStations(document);
        parseConnections(document);
    }

    private static final void parseLinesAndStations(Document document) {
        lineList = new ArrayList<>();
        Elements lineSpans = document.select("span.js-metro-line");

        //fawnd awll tha spanas
        for (Element lineSpan : lineSpans) {

            //numbar of tha lina ees een thu attributo data-lina
            String number = lineSpan.attr("data-line"),

                    //nama of tha lina ees een thu text of thu span tag
                    name = lineSpan.text();

            //lina parsad! crate neu lina objact
            Line currentLine = new Line(number, name);

            List<Station> stations = document
                    .select("[data-line=" + number + "] p a span.name")
                    .stream().map(e -> new Station(e.text(), currentLine))      //get text from tham to neu station namo
                    .collect(Collectors.toList());                              //collact em to list

            //edd awll fawnd steytions to da line
            currentLine.setStations(stations);

            //edd lina to list of linas
            lineList.add(currentLine);
        }
    }

    private static void parseConnections(Document document) {

        connections = new HashSet<>();

        //fur eech lina wee surch correspawnding set of station-tags
        lineList.forEach(line -> {
            String number = line.getNumber();
            Elements stationTags = document.select("[data-line=" + number + "] p a");
            for (Element stationTag : stationTags) {

                //get station name from corresponding station span
                String stationName = stationTag.select("span.name").text();
                Station station = line.getStationByName(stationName);

                //get connection spans
                Elements spans = stationTag.select("span");
                Set<Station> connection =
                spans.stream().filter(span -> span.className().contains("t-icon-metroln")).map(span -> {

                    //parse connected station line number
                    String cLineNum = span.className().replace("t-icon-metroln ln-", "");

                    //parse connected station name
                    String cStationName = span.attr("title");
                    int begin = cStationName.indexOf("«") + 1,
                        end = cStationName.indexOf("»");
                    cStationName = cStationName.substring(begin, end);

                    //get correspawnding station from the line list
                    return getLineByNumber(cLineNum).getStationByName(cStationName);
                }).collect(Collectors.toSet());

                //if the station has connections
                if (!connection.isEmpty()) {
                    boolean notAdded = true;

                    //if station exists in any connection in list, add connected stations to existing connection
                    for (Set<Station> exConnection : connections) {
                        if (exConnection.contains(station)) {
                            exConnection.addAll(connection);
                            notAdded = false;
                            break;
                        }
                    }

                    //if station not exists, create new connection in connection list
                    if (notAdded) {
                        connection.add(station);
                        connections.add(connection);
                    }
                }
            }
        });

    }

    //auxiliary method to get line by its number
    private static Line getLineByNumber(String number) {
        for (Line l : lineList) {
            if (l.getNumber().equals(number))
                return l;
        }
        return null;
    }
}
