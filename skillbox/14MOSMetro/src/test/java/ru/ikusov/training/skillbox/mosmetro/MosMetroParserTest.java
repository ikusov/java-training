package ru.ikusov.training.skillbox.mosmetro;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MosMetroParserTest extends TestCase {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        MosMetroParser.parseWebSite();
    }

    public void testButovo() {
        List<String> expected = new ArrayList<>(
                Arrays.asList("Битцевский парк",
                        "Лесопарковая",
                        "Улица Старокачаловская",
                        "Улица Скобелевская",
                        "Бульвар адмирала Ушакова",
                        "Улица Горчакова",
                        "Бунинская Аллея"));

        List<String> actual =
        MosMetroParser.lineList.stream()
                .filter(line -> line.getNumber().equals("12"))
                .flatMap(line -> line.getStations().stream())
                .map(station -> station.getName())
                .collect(Collectors.toList());

        Assert.assertEquals(actual, expected);
    }
}